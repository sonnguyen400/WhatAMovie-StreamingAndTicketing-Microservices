package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.common.util.StrUtils;
import com.sonnguyen.sniam.domain.command.CustomerCreateOrUpdateCmd;
import com.sonnguyen.sniam.infrastructure.support.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerEntity}
 */
@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Customer extends AuditingDomain {
    private UUID id;
    private UUID groupId;
    private UUID userId;
    private UUID customerAddressId;
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private CustomerType type;
    private LocalDate dateOfBirth;

    public Customer(CustomerCreateOrUpdateCmd cmd){
        this.id = IdUtils.nextId();
        this.fullName = cmd.getFullName();
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.email = cmd.getEmail();
        this.phoneNumber = cmd.getPhoneNumber();
        this.type = cmd.getType();
        this.dateOfBirth = cmd.getDateOfBirth();
    }

    public Customer(User user){
        this.id = IdUtils.nextId();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.fullName = StrUtils.concat(this.lastName, this.firstName);
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.type = CustomerType.REGISTERED;
        this.dateOfBirth = user.getDateOfBirth();
    }
}
