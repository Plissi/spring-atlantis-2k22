package com.usermngmnt.useratlantis;

import com.usermngmnt.useratlantis.entities.User;
import com.usermngmnt.useratlantis.enums.Role;
import com.usermngmnt.useratlantis.respositories.UserAtlantisRepository;
import com.usermngmnt.useratlantis.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserAtlantisApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAtlantisApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration, UserAtlantisRepository userAtlantisRepository, UserService userService){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(User.class);
/*            userService.addUser(new User(null, "user 1", "user1", "pass", Role.USER));
            userService.addUser(new User(null, "user 2", "user2", "pass", Role.USER));
            userService.addUser(new User(null, "user 3", "user3", "pass", Role.USER));
            userService.addUser(new User(null, "user 4", "user4", "pass", Role.ADMIN));*/
        };
    }

}
