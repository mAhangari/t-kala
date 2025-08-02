package com.tosan.tkala.domain;

import com.tosan.tkala.domain.enumuration.OrderState;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Order.withOrderItems",
                attributeNodes = @NamedAttributeNode(value = "orderItems")
        ),
        @NamedEntityGraph(
                name = "Order.withOrderItemsAndCustomer",
                attributeNodes = {
                        @NamedAttributeNode(value = "orderItems"),
                        @NamedAttributeNode(value = "customer")
                }
        ),
        @NamedEntityGraph(
                name = "Order.withOrderItemsAndProducts",
                attributeNodes = @NamedAttributeNode(value = "orderItems", subgraph = "orderItems.product"),
                subgraphs = {
                        @NamedSubgraph(
                                name = "orderItems.product",
                                attributeNodes = @NamedAttributeNode("product")
                        )
                }
        )
})
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Customer customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //@org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
    //@org.hibernate.annotations.BatchSize(size = 6)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private Set<OrderItem> orderItems = new HashSet<>();

    public void setOrderItems(Set<OrderItem> orderItems) {
        if (orderItems != null && !orderItems.isEmpty()) {
            orderItems.forEach(orderItem -> {
                getOrderItems().add(orderItem);
                orderItem.setOrder(this);
            });
        }
    }
}
