package com.toro.shoppingmall.service;

import com.toro.shoppingmall.domain.Member;
import com.toro.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return repository.findAll();
    }

    //단건조회
    public Member findOne(Long memberId){
        return repository.findOne(memberId);
    }


    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = repository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
