package com.tosan.tkala.Domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "STORE_NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

}
