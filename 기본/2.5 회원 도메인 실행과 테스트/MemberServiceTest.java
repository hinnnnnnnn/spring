⛓️‍💥test - hello.core - member - MemberServiceTest (클래스 생성)
📌MemberServiceTest.java
✅Junit 테스트로 검증
  
package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    // ❗ 이 부분이 DIP 위반(구현체에 직접 의존)
    MemberService memberService = new MemberServiceImpl(); //MemberService의 구현체인 MemberServiceImpl을 직접 생성하여 테스트에 사용
 
    @Test
    void join() {
        //given : 준비 단계 (Given)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 행동 단계 (When)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 검증 단계 (Then)
        Assertions.assertThat(member).isEqualTo(findMember);
    }

}


🚨 회원 도메인 설계의 문제점
• 다른 저장소로 변경할 떄 OCP 원칙을 잘 준수하는가?
• DIP를 잘 지키고 있는가?
• 의존 관계가 인터페이스 뿐만 아니라 구현까지 의존하는 문제점 발생
    -> 주문까지 만들고나서 문제점과 해결방안을 설명


 ✅ DIP (의존 역전 원칙) 위반
MemberService는 MemberRepository 인터페이스뿐만 아니라 MemoryMemberRepository 구현체에도 직접 의존하고 있습니다.
즉, 추상(인터페이스)과 구현 모두에 의존

✅ OCP (개방-폐쇄 원칙) 위반
현재 구조는 MemoryMemberRepository를 JdbcMemberRepository나 JpaMemberRepository 등으로 바꾸려면 코드를 직접 수정해야 함.
이는 변경에 닫혀있지 않음
