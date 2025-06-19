⛓️‍💥hello.core - order - OrderServiceImpl (클래스 생성)
📌OrderServiceImpl.java

package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;


public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); //회원 정보를 조회하기 위한 저장소 역할을 하는 MemberRepository를 메모리 기반 구현체로 생성
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //할인 정책을 고정 할인 방식(FixDiscountPolicy)으로 설정
    //🚨문제점: 구현체에 직접 의존하고 있어 DIP(의존성 역전 원칙)를 위반함(나중에 개선 필요
  
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원 ID로 회원 정보를 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //조회된 회원과 상품 가격을 이용해 할인 금액을 계산

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
