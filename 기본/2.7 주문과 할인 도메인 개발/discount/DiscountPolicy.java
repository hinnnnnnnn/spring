⛓️‍💥hello.core - discount - DiscountPolicy (인터페이스 생성)
📌DiscountPolicy.java
  
package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /* @return 할인 대상 금액 */
    int discount(Member member, int price); //회원과 가격을 받아서, 할인 금액을 계산해 반환
}
