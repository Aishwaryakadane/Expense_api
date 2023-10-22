package com.AK.test.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tables")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "fk_productId")
    Product product;


    @ManyToOne
    @JoinColumn(name = "fk_userId")
    User user;


    public Order(LocalDate orderDate, Product product, User user) {
        this.orderDate = orderDate;
        this.product = product;
        this.user = user;
    }
}
