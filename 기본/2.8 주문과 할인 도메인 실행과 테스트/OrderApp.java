⛓️‍💥hello.core - OrderApp (클래스 생성)
📌OrderApp.java
  
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        // 1) 회원 서비스와 주문 서비스 구현체 생성
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        // 2) 회원 생성 및 가입 처리
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 3) 회원이 아이템 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 4) 주문 정보 출력
        System.out.println("order = " + order);  //✅Order 클래스의 toString() 오버라이딩 메서드에 따라 주문 상세 출력됨
    }
}

/* 출력 결과
order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
 */
