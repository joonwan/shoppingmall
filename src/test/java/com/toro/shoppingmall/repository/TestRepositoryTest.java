package com.toro.shoppingmall.repository;

import com.toro.shoppingmall.domain.TestEntity;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest

class TestRepositoryTest {

    private TestRepository repository;

    @Autowired
    public TestRepositoryTest(TestRepository repository) {
        this.repository = repository;
    }

    @Test
    @Transactional
    public void save() throws Exception {
        //given

        TestEntity entity = new TestEntity();
        entity.setName("test1");
        entity.setAge(10);
        repository.save(entity);


//        //when
//        TestEntity findEntity = repository.findById(entity.getId());
//        //then
//
//        Assertions.assertThat(entity).isEqualTo(findEntity);
    }

}