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

        AppConfig appConfig = new AppConfig(); //⭐️추가⭐️
        MemberService memberService = appConfig.memberService(); //⭐️추가⭐️
        OrderService orderService = appConfig.orderService(); //⭐️추가⭐️

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order); //hello.core - order - Order.java에 있는 toSring으로 출력
    }
}
/* 출력 결과
order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
 */
