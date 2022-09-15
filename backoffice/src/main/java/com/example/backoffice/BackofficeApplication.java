package com.example.backoffice;

import com.example.backoffice.entities.Device;
import com.example.backoffice.entities.User;
import com.example.backoffice.entities.UserList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BackofficeApplication {
    private static final Logger log = LoggerFactory.getLogger(BackofficeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackofficeApplication.class, args);
    }

    @Bean
    RestTemplate getResTemplate(){
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner commandLineRunner(RestTemplate restTemplate){
        return args -> {

            String url = "http://localhost:8081/users/1";
            System.out.println(System.getenv());
            //String url = System.getenv("USER_SERVICE_URL") + 1;
            User user = restTemplate.getForObject(url, User.class);
            log.info(String.valueOf(user));

        };
    }
}
