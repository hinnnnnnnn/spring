📌AppConfig.java 수정
  
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //⭐️추가⭐️ 스프링 설정 클래스야 라고 알려주는 애너테이션
public class AppConfig {

    @Bean //⭐️추가⭐️ 해당 메서드가 리턴하는 객체를 스프링 빈으로 등록해주는 애너테이션
    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
    }

    @Bean //⭐️추가⭐️
    public MemoryMemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean //⭐️추가⭐️
    public OrderService orderService() {
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }

    @Bean //⭐️추가⭐️
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        // 출력 결과 : order = Order{memberId=1, itemName='itemA', itemPrice=20000, discountPrice=1000}
        return new RateDiscountPolicy();
        // 출력 결과 : order = Order{memberId=1, itemName='itemA', itemPrice=20000, discountPrice=2000}

    }
}
