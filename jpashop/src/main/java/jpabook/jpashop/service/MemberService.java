package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // jpa 조회 부분에서 성능 최적화
@RequiredArgsConstructor // final 필드를 파라미터로 하는 생성자 생성.
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional // 쓰기에서는 깐깐하게 해야. 기본으로 false 가 적용됨.
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); // 영속성 콘테스트에 올리는 순간 자동으로 id 를 설정
    }

    private void validateDuplicateMember(Member member){
        //EXCEPTION
        // 이렇게만 검증하기보다 db 에 unique 제약 조건을 추가로 하는게 좋음
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    
}
