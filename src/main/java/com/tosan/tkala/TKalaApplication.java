package com.tosan.tkala;

import com.tosan.tkala.Domain.Admin;
import com.tosan.tkala.Domain.Customer;
import com.tosan.tkala.Domain.User;
import com.tosan.tkala.repository.AdminRepository;
import com.tosan.tkala.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TKalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TKalaApplication.class, args);
    }

    @Bean
    public ApplicationRunner runApplication(UserRepository<User, Long> userRepository, AdminRepository adminRepository) {
        return exec -> {
            Customer user = new Customer();
            user.setFirstName("Morteza");
            user.setLastName("Ahangari");
            user.setMobileNumber("0911111111");

            Admin userOne = new Admin();
            userOne.setFirstName("Pedram");
            userOne.setLastName("Behradkian");
            userOne.setMobileNumber("0912111111");

            User savedUser = userRepository.save(user);
            User savedUserOne = userRepository.save(userOne);

            System.out.println(userRepository.findAll());
            /*System.out.println(adminRepository.findById(savedUserOne.getId()));*/


        };
    }

}
