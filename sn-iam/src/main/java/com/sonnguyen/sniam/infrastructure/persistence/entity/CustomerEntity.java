package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.sniam.infrastructure.support.enums.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customer_entity")
public class CustomerEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "group_id")
    private UUID groupId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "customer_address_id")
    private UUID customerAddressId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
