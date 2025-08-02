package com.tosan.tkala.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@org.hibernate.annotations.BatchSize(size = 50)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String color;

    private Integer productQuantity;

    public Product(String name, String color, Integer productQuantity) {
        this.name = name;
        this.color = color;
        this.productQuantity = productQuantity;
    }

    /*@Version
    private Long version;*/

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    /*@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)*/
    private Set<Store> stores = new HashSet<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    /*@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)*/
    private Set<OrderItem> orderItems = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
