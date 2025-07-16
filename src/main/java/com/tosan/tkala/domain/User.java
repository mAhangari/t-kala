package com.tosan.tkala.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", allocationSize = 30)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    private String lastName;

    @NotNull
    @Column(unique = true)
    private String mobileNumber;

    /*@AttributeOverride(name = "street", column = @Column(name = "home_street"))
    @AttributeOverride(name = "postalCode", column = @Column(name = "home_postalCode"))
    @AttributeOverride(name = "city", column = @Column(name = "home_city"))*/
    private Address homeAddress;

    /*private Address workAddress;*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
