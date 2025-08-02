package com.tosan.tkala.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
/*@Access(AccessType.PROPERTY)*/
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    /*@Access(AccessType.FIELD)*/
    private Long id;

    @Column(name = "STORE_NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID"/*, insertable = false, updatable = false*/)
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /*@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "STORE_PRODUCT_TABLE",
            joinColumns = @JoinColumn(name = "STORE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )*/
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
