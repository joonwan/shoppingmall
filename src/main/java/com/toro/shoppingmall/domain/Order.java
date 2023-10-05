package com.toro.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="orders")
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    private List<OrderItems> orderItems = new ArrayList<>();

    private Delivery delivery;

    private LocalDateTime orderDateTime;

    private OrderStatus orderStatus;





}
