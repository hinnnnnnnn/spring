📌RateDiscountPolicy.java에 추가
  
package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

⭐️@Component⭐️
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPolicy = 10;

    @Override
    public int discount(Member member, int price) { //discount에서 command + shift + T : 새 테스트 생성
        if (member.getGrade() == Grade.VIP) {
            return price * discountPolicy / 100;
        } else {
            return 0;
        }
    }
}
