⛓️‍💥hello.core - MemberApp (클래스 생성)
📌MemberApp.java
✅자바로 작성 (스프링 없이 직접 의존성 주입(DI)을 수동으로 구현한 구조)
  
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) { //🔎psvm
        MemberService memberService = new MemberServiceImpl(); //실제 로직은 구현체인 MemberServiceImpl에서 처리
        Member member = new Member(1L, "memberA", Grade.VIP); //ID가 1L이고 이름이 "memberA"이며 등급이 VIP인 회원 객체를 생성
        memberService.join(member); //회원 등록, 내부적으로 MemberRepository에 저장

        Member findMember = memberService.findMember(1L); //ID 1L인 회원을 다시 조회
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

/* 출력 결과
new member = memberA
find Member = memberA
 */
