⛓️‍💥hello.core - autowired - AllBeanTest
📌AllBeanTest.java
  
package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member,10000,"fixDiscountPolicy"); //policyMap.get("fixDiscountPolicy")로 FixDiscountPolicy 사용 → 1000 할인

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member,20000,"rateDiscountPolicy"); //RateDiscountPolicy 사용 → 2000 할인
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) { //DiscountService 생성자
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode); 
            return discountPolicy.discount(member, price);
        }
    }
}

/* 출력 결과
policyMap = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@6138e79a, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@2dcd168a}
policies = [hello.core.discount.FixDiscountPolicy@6138e79a, hello.core.discount.RateDiscountPolicy@2dcd168a]
 */
