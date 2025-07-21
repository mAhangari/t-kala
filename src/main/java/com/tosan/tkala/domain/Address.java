package com.tosan.tkala.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    /*@Access(AccessType.FIELD)*/
    private Long id;

    private String street;

    private String postalCode;

    private String city;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

}
