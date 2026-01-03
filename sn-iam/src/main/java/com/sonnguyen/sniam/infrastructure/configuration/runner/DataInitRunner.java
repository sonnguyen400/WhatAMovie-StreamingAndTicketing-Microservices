package com.sonnguyen.sniam.infrastructure.configuration.runner;

import com.sonnguyen.common.web.security.UserPasswordEncoder;
import com.sonnguyen.sniam.application.service.UserCommandService;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.domain.User;
import com.sonnguyen.sniam.domain.command.UserCreateOrUpdateCmd;
import com.sonnguyen.sniam.domain.repository.RoleRepository;
import com.sonnguyen.sniam.domain.repository.UserRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.PermissionEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class DataInitRunner implements CommandLineRunner {
    UserRepository userRepository;
    RoleRepository roleRepository;
    Perm
    UserPasswordEncoder userPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        this.initRootUser();

    }

    private void initRootRoles(){
        Role role = new Role()
    }

    private void initRolePermissions(){

    }

    private void initRootUser(){
        Optional<User> user = this.userRepository.findByUsername("admin");
        if(user.isEmpty()){
            UserCreateOrUpdateCmd userCreateOrUpdateCmd = UserCreateOrUpdateCmd.builder()
                    .username("admin")
                    .password("123456aA@")
                    .firstName("System")
                    .lastName("Administrator")
                    .email("")
                    .phoneNumber("")
                    .locked(false)
                    .verified(true)
                    .enabled(true)
                    .root(true)
                    .build();
            this.userRepository.save(new User(userCreateOrUpdateCmd, new ArrayList<>(), this.userPasswordEncoder));
        }
    }
}
