package com.tosan.tkala.Domain;

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
//@DiscriminatorColumn(name = "DISCRIMINATOR_COLUMN")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", allocationSize = 30)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull
    private String firstName;

    private String lastName;

    private String mobileNumber;

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
