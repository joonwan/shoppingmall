package com.toro.shoppingmall.service;

import com.toro.shoppingmall.domain.Address;
import com.toro.shoppingmall.domain.Member;
import com.toro.shoppingmall.domain.Order;
import com.toro.shoppingmall.domain.OrderStatus;
import com.toro.shoppingmall.domain.item.Book;
import com.toro.shoppingmall.domain.item.Item;
import com.toro.shoppingmall.excption.NotEnoughStockException;
import com.toro.shoppingmall.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService service;

    @Autowired
    OrderRepository repository;
    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Item book = createBook();

        //when
        int orderCount = 2;
        Long orderId = service.order(member.getId(), book.getId(), orderCount);
        Order findOrder = repository.findOne(orderId);

        //then

        Assert.assertEquals("상품 주문시 상태는 Order", OrderStatus.ORDER,findOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수 가 정확해야 한다.",1,findOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격*수량이다.",20000,findOrder.getTotalPrice() );
        Assert.assertEquals("주문 수량만큼 재고가 감소해야한다.",8, book.getStockQuantity());
    }



    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook();
        Long orderId = service.order(member.getId(), book.getId(), 2);

        //when
        service.cancelOrder(orderId);
        //then
        Order findOrder = repository.findOne(orderId);
        org.assertj.core.api.Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        org.assertj.core.api.Assertions.assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    @Test
    public void 상품제고초과() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook();

        //when
        Assertions.assertThrows(NotEnoughStockException.class,
                ()->service.order(member.getId(),book.getId(),11));
        //then
    }

    private Item createBook() {
        Item book = new Book();
        book.setName("book1");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member("member1");
        member.setAddress(new Address("busan","street1","10000"));
        em.persist(member);
        return member;
    }
}