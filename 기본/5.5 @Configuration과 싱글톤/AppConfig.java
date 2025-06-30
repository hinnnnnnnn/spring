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

@Configuration
public class AppConfig {
    
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        // 출력 결과 : order = Order{memberId=1, itemName='itemA', itemPrice=20000, discountPrice=1000}
        return new RateDiscountPolicy();
        // 출력 결과 : order = Order{memberId=1, itemName='itemA', itemPrice=20000, discountPrice=2000}

    }
}

🔎 의문점
@Bean memberService -> new MemoryMemberRepository()
@Bean orderService -> new MemoryMemberRepository()
memberService 빈을 만드는 코드를 보면 MemberRepository를 호출함, 이 메서드를 호출하면 new MemoryMemberRepository()를 호출함
orderService 빈을 만드는 코드도 동일하게 MemberRepository를 호출함, 이 메서드를 호출하면 new MemoryMemberRepository()를 호출함
싱글톤이 깨지는 것 처럼 보임

/* 출력 결과
  call AppConfig.memberService
  call AppConfig.memberRepository
  call AppConfig.orderService
*/ -> 싱글톤
