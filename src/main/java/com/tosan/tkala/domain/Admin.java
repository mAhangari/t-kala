package com.tosan.tkala.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin extends User {

    private String nationalId;

    @Override
    public String toString() {
        return "Admin{" +
                super.toString()
                +
                "nationalId='" + nationalId + '\'' +
                '}';
    }
}
