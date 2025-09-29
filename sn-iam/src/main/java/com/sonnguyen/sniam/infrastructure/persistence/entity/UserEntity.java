package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.jpa.entity.AuditingEntity;
import com.sonnguyen.common.util.Validator;
import com.sonnguyen.sniam.infrastructure.constant.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_detail")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = Validator.Length.UUID_MAX_LENGTH)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, length = Validator.Length.USERNAME_MAX_LENGTH)
    private String username;

    @Column(name = "password", nullable = false, length = Validator.Length.PASSWORD_MAX_LENGTH)
    private String password;

    @Column(name = "first_name", length = Validator.Length.FIRST_NAME_MAX_LENGTH)
    private String firstName;

    @Column(name = "last_name", length = Validator.Length.LAST_NAME_MAX_LENGTH)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = Validator.Length.EMAIL_MAX_LENGTH)
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = Validator.Length.ENUM_MAX_LENGTH)
    private Gender gender;

    @Column(name = "phone_number", length = Validator.Length.PHONE_NUMBER_MAX_LENGTH)
    private String phoneNumber;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "deleted")
    private Boolean deleted;
}
