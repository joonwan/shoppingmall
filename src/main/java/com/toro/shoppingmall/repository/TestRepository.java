package com.toro.shoppingmall.repository;

import com.toro.shoppingmall.domain.TestEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TestRepository {

    @PersistenceContext
    EntityManager em;


    public Long save(TestEntity te){
        em.persist(te);
        return te.getId();
    }

    public TestEntity findById(long id){
        return em.find(TestEntity.class,id);
    }

}
