package com.tosan.tkala.domain;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private Collection<Order> orders = new ArrayList<>();

}
