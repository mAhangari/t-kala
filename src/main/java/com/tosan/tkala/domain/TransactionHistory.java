package com.tosan.tkala.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String userId;

    private String trackerId;

    @Column(length = 1000)
    private String data;

    @CreationTimestamp
    private LocalDateTime createdDateTime;
}
