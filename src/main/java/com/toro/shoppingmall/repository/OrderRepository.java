package com.toro.shoppingmall.repository;

import com.toro.shoppingmall.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long orderId){
        return em.find(Order.class, orderId);
    }

//    public List<Order> findAll(OrderSearch orderSearch){
//        // todo
//        return null;
//    }
}
