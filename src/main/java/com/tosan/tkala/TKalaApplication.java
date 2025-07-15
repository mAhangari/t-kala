package com.tosan.tkala;

import com.tosan.tkala.Domain.*;
import com.tosan.tkala.repository.AdminRepository;
import com.tosan.tkala.repository.StoreRepository;
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
    public ApplicationRunner runApplication(UserRepository<User, Long> userRepository,
                                            AdminRepository adminRepository,
                                            StoreRepository storeRepository) {
        return exec -> {
            Customer user = new Customer();
            user.setFirstName("Morteza");
            user.setLastName("Ahangari");
            user.setMobileNumber("0911111111");

            Admin userOne = Admin.builder().nationalId("2150382342").build();
            userOne.setFirstName("Pedram");
            userOne.setLastName("Behradkian");
            userOne.setMobileNumber("0912111111");

            StoreOwner storeOwner = new StoreOwner();
            storeOwner.setFirstName("Mohammad");
            storeOwner.setLastName("Faalian");
            storeOwner.setMobileNumber("0912345678");

            User savedUser = userRepository.save(user);
            User savedUserOne = userRepository.save(userOne);
            //User savedStoreOwner = userRepository.save(storeOwner);

            Store store = new Store();
            store.setName("Shahi Store");
            //store.setUser(savedStoreOwner);


            Store savedStoreOwner = storeRepository.save(store);
            storeOwner.getStores().add(savedStoreOwner);

            userRepository.save(storeOwner);

            System.out.println(userRepository.findAll());
            /*System.out.println(adminRepository.findById(savedUserOne.getId()));*/


        };
    }

}
