📌OrderServiceImpl.java

package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;


public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; //⭐️수정⭐️
    private final DiscountPolicy discountPolicy; //⭐️수정⭐️

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { //⭐️생성자 추가⭐️
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

✅ 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않음 
   단지 DiscountPolicy 인터페이스만 의존함
   OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객가 들어올지는 알 수 없음
   OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig에서 결정됨
   OrderServiceImpl은 이제부터 실행에만 집중하면 됨
  
   OrderServiceImpl에는 MemoryMemberRepository, FixDiscountPolicy 객체의 의존관계가 주입됨
