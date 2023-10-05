package com.toro.shoppingmall.repository;

import com.toro.shoppingmall.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long memberId){
        return em.find(Member.class, memberId);
    }

    //회원 리스트 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member as m ", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
