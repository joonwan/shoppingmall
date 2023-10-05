package com.toro.shoppingmall.domain.item;

import com.toro.shoppingmall.domain.Category;
import com.toro.shoppingmall.excption.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="iteam_id")
    private Long id;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    private String name;
    private int price;
    private int stockQuantity;


    // 제고 증가
    public void addStockQuantity(int quantity){
        this.stockQuantity += quantity ;
    }

    // 제고 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("재고량이 부족합니다.");
        }
        this.stockQuantity = restStock;
    }

}
