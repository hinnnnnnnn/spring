⛓️‍💥hello.core - AppConfig
📌AppConfig.java
  
package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}

✅실제 동작에 필요한 구현 객체 생성  
MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy
  
✅생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결)해줌 
MemberServiceImpl -> MemoryMemberRepository
OrderService -> MemoryMemberRepository,FixDiscountPolicy
