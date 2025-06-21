📌MemberApp.java

package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) { //psvm
      
        AppConfig appConfig = new AppConfig(); //⭐️추가⭐️
        MemberService memberService = appConfig.memberService(); //⭐️추가⭐️
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

/* 출력 결과
new member = memberA
find Member = memberA
 */
