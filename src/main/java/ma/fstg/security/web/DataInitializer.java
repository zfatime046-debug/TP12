package ma.fstg.security.web;

import ma.fstg.security.entities.Role;
import ma.fstg.security.entities.User;
import ma.fstg.security.repositories.UserRepository;
import ma.fstg.security.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepo,
                               RoleRepository roleRepo,
                               PasswordEncoder passwordEncoder) {
        return args -> {

            if (roleRepo.count() == 0) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepo.save(adminRole);

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setActive(true);
                admin.addRole(adminRole);

                userRepo.save(admin);
            }
        };
    }
}