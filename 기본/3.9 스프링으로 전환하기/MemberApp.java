📌MemberApp.java 수정
  
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) { //psvm

        //⭐️추가⭐️
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //스프링 컨테이너 생성 + AppConfig 설정에 따라 객체 등록
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //스프링 컨테이너에서 memberService 객체 꺼내오기

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
