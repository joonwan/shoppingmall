package com.toro.shoppingmall.service;

import com.toro.shoppingmall.domain.Member;
import com.toro.shoppingmall.repository.MemberRepository;
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
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository;

    @Test
    public void save() throws Exception {
        //given
        Member member = new Member("testUser");
        //when

        Long memberId = memberService.join(member);

        Member findMember = memberService.findOne(memberId);
        //then

        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test()
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member1");


        //when
        memberService.join(member1);
        System.out.println("===========");


        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}