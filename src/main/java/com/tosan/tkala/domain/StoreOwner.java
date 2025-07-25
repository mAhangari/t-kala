package com.tosan.tkala.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class StoreOwner extends User {

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}/*, orphanRemoval = true*/, fetch = FetchType.LAZY)
    /*@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)*/
    /*@JoinColumn(name = "USER_ID", nullable = true*//*, insertable = false, updatable = false*//*)*/
    /*@OrderColumn*/
    private Collection<Store> stores = new ArrayList<>();
}
