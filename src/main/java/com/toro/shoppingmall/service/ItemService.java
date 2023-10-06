package com.toro.shoppingmall.service;

import com.toro.shoppingmall.domain.item.Item;
import com.toro.shoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    @Transactional
    public Long saveItem(Item item){
        return repository.save(item);
    }

    public List<Item> findItems(){
        return repository.findAll();
    }

    public Item findOne(Long itemId){
        return repository.findOne(itemId);
    }

}
