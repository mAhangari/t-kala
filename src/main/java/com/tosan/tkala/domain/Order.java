package com.tosan.tkala.domain;

import com.tosan.tkala.domain.enumuration.OrderState;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String trackerId;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<OrderItem> orderItems = new HashSet<>();
}
