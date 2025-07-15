package com.tosan.tkala.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class StoreOwner extends User {

    @OneToMany//(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Store> stores = new HashSet<>();
}
