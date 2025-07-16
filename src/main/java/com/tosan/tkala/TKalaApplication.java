package com.tosan.tkala;

import com.tosan.tkala.domain.*;
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
            /*store.setUser(storeOwner);*/


//            Store savedStoreOwner = storeRepository.save(store);
            storeOwner.getStores().add(store);

            /*userRepository.save(storeOwner);*/
            /*userRepository.save(userWithAddress());*/

            userRepository.save(storeOwnerWithMultipleStore());

            userRepository.delete(userRepository.findById(3L).get());


            /*Store store1 = storeRepository.findById(1L).get();
            store1.setName("Shahi Store 2");
            Thread.sleep(3000);
            storeRepository.save(store1);

            System.out.println(storeRepository.findById(1L).get());;*/

            System.out.println(userRepository.findAll());
            /*System.out.println(adminRepository.findById(savedUserOne.getId()));*/



        };
    }


    private User userWithAddress() {

        Address homeAddress = new Address();
        homeAddress.setCity("Shahi");
        homeAddress.setPostalCode("11212");
        homeAddress.setStreet("zerafat");

        Address workAddress = new Address();
        workAddress.setStreet("palizvani");
        workAddress.setCity("Tehran");
        workAddress.setPostalCode("12121414153");

        StoreOwner storeOwner = new StoreOwner();
        storeOwner.setFirstName("Mohammad");
        storeOwner.setLastName("Faalian");
        storeOwner.setMobileNumber("0912345678");
        storeOwner.setHomeAddress(homeAddress);
        /*storeOwner.setWorkAddress(workAddress);*/

        return storeOwner;
    }

    private User storeOwnerWithMultipleStore() {
        StoreOwner storeOwner = new StoreOwner();
        storeOwner.setFirstName("Mohammad");
        storeOwner.setLastName("Faalian");
        storeOwner.setMobileNumber("0912345678");

        Store store1 = new Store();
        store1.setName("Shahi Store");
        store1.setUser(storeOwner);

        Store store2 = new Store();
        store2.setName("Tehran Store");
        store2.setUser(storeOwner);

        storeOwner.getStores().add(store1);
        storeOwner.getStores().add(store2);

        return storeOwner;
    }

}
