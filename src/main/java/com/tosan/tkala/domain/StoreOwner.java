package com.tosan.tkala.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class StoreOwner extends User {

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
    /*@JoinColumn(name = "USER_ID")*/
    /*@OrderColumn*/
    private Set<Store> stores = new HashSet<>();
}
