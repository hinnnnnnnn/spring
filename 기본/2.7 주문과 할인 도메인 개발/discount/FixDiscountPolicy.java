⛓️‍💥hello.core - discount - FixDiscountPolicy
📌FixDiscountPolicy.java
  
package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) { //메서드는 회원 등급을 확인해서 VIP면 1000원 할인, 아니면 0원을 반환
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
