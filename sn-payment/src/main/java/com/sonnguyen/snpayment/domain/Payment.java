package com.sonnguyen.snpayment.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.snpayment.domain.command.IdempotencyCreateCmd;
import com.sonnguyen.snpayment.domain.command.PaymentCreateCommand;
import com.sonnguyen.snpayment.domain.command.PaymentDetailCreateCmd;
import com.sonnguyen.snpayment.domain.command.TransactionCreateCommand;
import com.sonnguyen.snpayment.infrastructure.constant.PaymentGatewayType;
import com.sonnguyen.snpayment.infrastructure.constant.PaymentStatus;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionDomainType;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionStatus;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Payment extends AuditingDomain {
    private UUID id;
    private UUID merchantAccountId;
    private String externalOrderId;
    private UUID customerId;
    private String currency;
    private BigDecimal amount;
    private PaymentStatus status;
    private String description;
    private String metadata;
    private Boolean deleted;
    private Long version;
    private String code;
    private UUID idempotencyKeyId;
    private String qrCode;
    private String redirectLink;
    private UUID domainId;
    private DomainType domainType;

    //Enrich
    private List<PaymentAttempt> paymentAttempts;
    private List<Transaction> transactions;
    private MerchantAccount merchantAccount;
    private IdempotencyKey idempotencyKey;
    private String idempotencyKeyStr;
    private List<PaymentDetail> paymentDetails;

    public Payment(PaymentCreateCommand cmd, MerchantAccount merchantAccount) {
        this.externalOrderId = cmd.getExternalOrderId();
        this.currency = cmd.getCurrency();
        this.description = cmd.getDescription();
        this.metadata = cmd.getMetadata();
        this.deleted = false;
        this.customerId = cmd.getCustomerId();
        this.processDetails(cmd.getDetails());
        this.createTransaction(cmd.getTransactionStatus(), merchantAccount);
        this.initStatus(merchantAccount);
        this.createIdempotencyKey(cmd.getIdempotencyKey(), cmd.getExpiredAt());
    }

    private void processDetails(List<PaymentDetailCreateCmd> details) {
        if(CollectionUtils.isEmpty(details)) {
            this.amount = BigDecimal.ZERO;
            return;
        }
        this.amount = details.stream()
                .map(PaymentDetailCreateCmd::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.paymentDetails = details.stream().map(it->new PaymentDetail(this, it)).toList();
    }

    public void createTransaction(TransactionStatus status, MerchantAccount merchantAccount) {
        if (Objects.isNull(this.transactions)) {
            this.transactions = new ArrayList<>();
        }
        Transaction transaction = null;
        if (PaymentGatewayType.CASH.equals(merchantAccount.getPaymentGatewayType())) {
            TransactionCreateCommand transactionCreateCommand = TransactionCreateCommand.builder()
                    .type(TransactionType.SETTLEMENT)
                    .status(status)
                    .amount(this.amount)
                    .currency(this.currency)
                    .domainType(TransactionDomainType.PAYMENT)
                    .domainId(this.id)
                    .status(status)
                    .gateway(merchantAccount.getConfig())
                    .build();
            transaction = new Transaction(transactionCreateCommand);
        } else if (PaymentGatewayType.ZALOPAY.equals(merchantAccount.getPaymentGatewayType())) {
            TransactionCreateCommand transactionCreateCommand = TransactionCreateCommand.builder()
                    .type(TransactionType.SETTLEMENT)
                    .status(status)
                    .amount(this.amount)
                    .currency(this.currency)
                    .domainType(TransactionDomainType.PAYMENT)
                    .domainId(this.id)
                    .gateway(merchantAccount.getConfig())
                    .build();
            transaction = new Transaction(transactionCreateCommand);
        } else {
            TransactionCreateCommand transactionCreateCommand = TransactionCreateCommand.builder()
                    .type(TransactionType.CAPTURE)
                    .status(status)
                    .amount(this.amount)
                    .currency(this.currency)
                    .domainType(TransactionDomainType.PAYMENT)
                    .domainId(this.id)
                    .gateway(merchantAccount.getConfig())
                    .build();
        }
        if (Objects.nonNull(transaction)) {
            this.transactions.add(transaction);
        }
    }

    public void initStatus(MerchantAccount merchantAccount) {
        if (PaymentGatewayType.CASH.equals(merchantAccount.getPaymentGatewayType())) {
            this.status = PaymentStatus.CREATED;
        } else {
            this.status = PaymentStatus.PENDING;
        }
    }

    public void createIdempotencyKey(String key, Instant expired) {
        IdempotencyCreateCmd cmd = IdempotencyCreateCmd.builder()
                .idempotencyKey(key)
                .expiredAt(expired)
                .build();
        IdempotencyKey idempotencyKey = new IdempotencyKey(cmd);
        this.idempotencyKeyId = idempotencyKey.getId();
    }

    public void enrichAttempts(List<PaymentAttempt> paymentAttempts) {
        this.paymentAttempts = paymentAttempts;
    }

    public void enrichIdempotencyKey(IdempotencyKey idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        this.idempotencyKeyStr = idempotencyKey.getKey();
    }

    public void enrichTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
