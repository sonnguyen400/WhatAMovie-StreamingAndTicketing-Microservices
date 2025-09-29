package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.common.web.security.UserPasswordEncoder;
import com.sonnguyen.sniam.domain.command.UserCreateOrUpdateCmd;
import com.sonnguyen.sniam.infrastructure.constant.Gender;
import com.sonnguyen.sniam.infrastructure.error.BadRequestError;
import com.sonnguyen.sniam.infrastructure.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class User extends AuditingDomain {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private Boolean locked;
    private Boolean verified;
    private Boolean enabled;
    private Boolean deleted;
    public List<UserTrace> traces;
    private List<UserRole> roles;

    public User(UserCreateOrUpdateCmd cmd, List<Role> roles, UserPasswordEncoder passwordEncoder) {
        this.id = IdUtils.nextId();
        this.username = cmd.getUsername();
        this.password = passwordEncoder.encode(cmd.getPassword());
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.email = cmd.getEmail();
        this.dateOfBirth = cmd.getDateOfBirth();
        this.gender = cmd.getGender();
        this.phoneNumber = cmd.getPhoneNumber();
        this.locked = false;
        this.verified = false;
        this.enabled = true;
        this.deleted = false;
        this.updateRoles(roles);
    }

    public User(UserCreateOrUpdateCmd cmd, List<Role> roles) {
        this.username = cmd.getUsername();
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.gender = cmd.getGender();


    }

    public void updateRoles(List<Role> newRoles) {
        if (Objects.isNull(this.roles)) {
            this.roles = new ArrayList<>();
        }
        this.roles.forEach(UserRole::delete);

        Map<UUID, UserRole> roleMap = this.roles.stream()
                .collect(Collectors.toMap(UserRole::getRoleId, Function.identity()));

        for (Role role : newRoles) {
            UserRole userRole = roleMap.getOrDefault(role.getId(), null);
            if (Objects.nonNull(userRole)) {
                userRole.unDelete();
            } else {
                this.roles.add(new UserRole(this, role));
            }
        }
    }

    public UserAuthority login(String password, UserPasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new ResponseException(BadRequestError.INVALID_USER_NAME_PASSWORD);
        }
        if (Boolean.TRUE.equals(this.locked)) {
            throw new ResponseException(BadRequestError.LOCKED_USER);
        }
        if (Boolean.FALSE.equals(this.verified)) {
            throw new ResponseException(BadRequestError.UNVERIFIED_USER);
        }
        if (Boolean.FALSE.equals(this.enabled)) {
            throw new ResponseException(NotFoundError.USER_NOT_FOUND);
        }
        if (CollectionUtils.isNotEmpty(this.traces)) {
            List<UserTrace> lastTraces = traces.stream()
                    .sorted(Comparator.comparing(UserTrace::getCreatedAt).reversed())
                    .limit(5)
                    .toList();

            boolean allLoginFailFalse = lastTraces.stream()
                    .allMatch(it->!it.getLoginSuccess());

            if (allLoginFailFalse) {
                throw new ResponseException(BadRequestError.CONSECUTIVE_LOGIN_FAIL);
            }
        }
        return this.getAuthority();
    }

    public UserAuthority getAuthority() {
        UserAuthority authority = new UserAuthority();
        authority.setUserId(this.id);
        authority.setUserName(this.username);
        authority.setEmail(this.email);
        authority.setLocked(this.locked);
        authority.setVerified(this.verified);
        authority.setEnabled(this.enabled);
        if (CollectionUtils.isNotEmpty(this.roles)) {
            List<String> authorities = this.roles.stream()
                    .flatMap(userRole -> userRole.getPermissions().stream()
                            .map(rolePermission -> String.format("%s.%s", userRole.getRoleCode(), rolePermission.getPermissionCode()))
                    )
                    .toList();
            authority.setAuthorities(authorities);
        }
        return authority;
    }

    public void delete() {
        this.deleted = true;
    }

    public void verify() {
        this.verified = true;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public void disable() {
        this.enabled = false;
    }

    public void enrichRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
