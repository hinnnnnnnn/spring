⛓️‍💥test - hello.core - order - OrderServiceTest (클래스 생성)
📌OrderServiceTest.java
✅Junit 테스트로 검증
    
package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() { 
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP); //ID 1L, 이름 "memberA", 등급 VIP인 회원을 생성 후 join() 메서드로 저장
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000); //해당 회원이 itemA 상품을 10,000원에 주문, OrderServiceImpl에서 회원 정보를 조회하고, 할인 정책 적용 후 주문 생성

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
