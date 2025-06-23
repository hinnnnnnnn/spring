📌OrderApp.java 추가
  
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        //⭐️추가⭐️
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //스프링 컨테이너 생성 + AppConfig 설정에 따라 객체 등록

        //⭐️추가⭐️
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //스프링 컨테이너에서 memberService 객체 꺼내오기
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class); //스프링 컨테이너에서 orderService 객체 꺼내오기

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order); //hello.core - order - Order.java에 있는 toSring으로 출력
    }
}
/* 출력 결과
order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
 */
