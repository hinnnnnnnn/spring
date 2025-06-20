⛓️‍💥hello.core - discount - RateDiscountPolicy
📌RateDiscountPolicy.javapackage hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPolicy = 10;

    @Override
    public int discount(Member member, int price) { //discount에서 command + shift + T : 새 테스트 생성
        if (member.getGrade() == Grade.VIP) {
            return price * discountPolicy / 100; //price * 10 / 100 → 10% 할인을 계산해 반환
        } else {
            return 0;
        }
    }
}
