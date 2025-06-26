⛓️‍💥test - beanfind - ApplicationContextSameBeanFindTest
📌ApplicationContextSameBeanFindTest.java

package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class)); 
      //🔎MemberRepository 타입의 빈이 두 개 정의되어 있기 때문에, 타입만으로 조회하면 어떤 빈을 줄지 모름
      //그래서 NoUniqueBeanDefinitionException 예외 발생
      //🚨org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'hello.core.member.MemberRepository' available: expected single matching bean but found 2: memberRepository1,memberRepository2
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
      //🔎이름을 명시적으로 지정하면 어떤 빈을 원하는지 명확해져서 정상적으로 주입이 됨
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beanOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
      //🔎특정 타입 (MemberRepository)의 빈을 모두 Map 형태로 조회
    }

    @Configuration 
    static class SameBeanConfig { //✅MemberRepository 타입의 빈을 이름만 다르게 해서 두 개 등록

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}

/* ⭐️ 출력 결과 ⭐️
key = memberRepository1value = hello.core.member.MemoryMemberRepository@42463763
key = memberRepository2value = hello.core.member.MemoryMemberRepository@59f63e24
beanOfType = {memberRepository1=hello.core.member.MemoryMemberRepository@42463763, memberRepository2=hello.core.member.MemoryMemberRepository@59f63e24}
 */
