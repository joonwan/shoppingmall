package com.toro.shoppingmall.repository;

import com.toro.shoppingmall.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item){
       if(item.getId() == null){
           em.persist(item);
       }else{
           em.merge(item);
       }
       return item.getId();
    }

    public Item findOne(Long itemId){
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i ",Item.class).getResultList();
    }

}
