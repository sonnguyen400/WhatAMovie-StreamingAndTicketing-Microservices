package com.sonnguyen.snpayment.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snpayment.domain.IdempotencyKey;
import com.sonnguyen.snpayment.domain.Payment;
import com.sonnguyen.snpayment.domain.PaymentAttempt;
import com.sonnguyen.snpayment.domain.Transaction;
import com.sonnguyen.snpayment.domain.repository.PaymentRepository;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionDomainType;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.PaymentEntity;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.IdempotencyKeyEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.PaymentAttemptEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.PaymentEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.TransactionEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.IdempotencyKeyEntityRepository;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.PaymentAttemptEntityRepository;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.PaymentEntityRepository;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.TransactionEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class PaymentRepositoryImpl extends AbstractDomainRepository<Payment, PaymentEntity, UUID>
        implements PaymentRepository {

    private PaymentEntityRepository paymentEntityRepository;
    private PaymentEntityMapper paymentEntityMapper;
    private PaymentAttemptEntityRepository paymentAttemptEntityRepository;
    private PaymentAttemptEntityMapper paymentAttemptEntityMapper;
    private IdempotencyKeyEntityRepository idempotencyKeyEntityRepository;
    private IdempotencyKeyEntityMapper idempotencyKeyEntityMapper;
    private TransactionEntityRepository transactionEntityRepository;
    private TransactionEntityMapper transactionEntityMapper;

    public PaymentRepositoryImpl(PaymentEntityRepository paymentEntityRepository,
                                 PaymentEntityMapper paymentEntityMapper,
                                 PaymentAttemptEntityRepository paymentAttemptEntityRepository,
                                 PaymentAttemptEntityMapper paymentAttemptEntityMapper,
                                 IdempotencyKeyEntityRepository idempotencyKeyEntityRepository,
                                 IdempotencyKeyEntityMapper idempotencyKeyEntityMapper,
                                 TransactionEntityRepository transactionEntityRepository,
                                 TransactionEntityMapper transactionEntityMapper) {
        super(paymentEntityRepository, paymentEntityMapper);
        this.paymentEntityRepository = paymentEntityRepository;
        this.paymentEntityMapper = paymentEntityMapper;
        this.paymentAttemptEntityRepository = paymentAttemptEntityRepository;
        this.paymentAttemptEntityMapper = paymentAttemptEntityMapper;
        this.idempotencyKeyEntityMapper = idempotencyKeyEntityMapper;
        this.idempotencyKeyEntityRepository = idempotencyKeyEntityRepository;
        this.transactionEntityRepository = transactionEntityRepository;
        this.transactionEntityMapper = transactionEntityMapper;
    }

    @Override
    public Collection<Payment> enrichAll(Collection<Payment> domains) {
        List<UUID> paymentIds = new ArrayList<>();
        List<UUID> idempotencyKeyIds = new ArrayList<>();
        domains.forEach(it -> {
            paymentIds.add(it.getId());
            idempotencyKeyIds.add(it.getIdempotencyKeyId());
        });
        Map<UUID, List<PaymentAttempt>> paymentAttempts = this.paymentAttemptEntityMapper
                .toDomain(this.paymentAttemptEntityRepository.findByPaymentIds(paymentIds))
                .stream()
                .collect(Collectors.groupingBy(PaymentAttempt::getPaymentId));
        Map<UUID, IdempotencyKey> idempotencyKeyMap = this.idempotencyKeyEntityMapper
                .toDomain(this.idempotencyKeyEntityRepository.findAllById(idempotencyKeyIds))
                .stream()
                .collect(Collectors.toMap(IdempotencyKey::getId, Function.identity()));
        Map<UUID, List<Transaction>> trasactionMap = this.transactionEntityMapper
                .toDomain(this.transactionEntityRepository.findAllByDomainKeys(paymentIds, TransactionDomainType.PAYMENT))
                .stream()
                .collect(Collectors.groupingBy(Transaction::getDomainId));
        domains.forEach(payment -> {
            payment.enrichAttempts(paymentAttempts.getOrDefault(payment.getId(), List.of()));
            payment.enrichTransactions(trasactionMap.getOrDefault(payment.getId(), List.of()));
            Optional.of(idempotencyKeyMap.getOrDefault(payment.getIdempotencyKeyId(), null))
                    .ifPresent(payment::enrichIdempotencyKey);
        });
        return domains;
    }

    @Override
    public Optional<Payment> findByIdempotencyKey(String idempotencyKey) {
        return this.paymentEntityRepository.findByIdempotencyKey(idempotencyKey)
                .<Payment>map(this.paymentEntityMapper::toDomain)
                .map(this::enrich);
    }
}
