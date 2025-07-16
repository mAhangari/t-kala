package com.tosan.tkala.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class Address {

    private String street;

    private String postalCode;

    private String city;

}
