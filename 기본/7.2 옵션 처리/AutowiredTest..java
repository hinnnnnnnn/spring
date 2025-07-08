⛓️‍💥test - autowired - AutowiredTest
📌AutowiredTest.java
  
package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //호출 안됨
        @Autowired(required = false) 
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
      
        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
      
        //optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}


/* 출력 결과
noBean2 = null
noBean3 = Optional.empty
 */

⭐️ Member는 스프링 빈이 아님
   setNoBean1()은 호출 자체가 안됨
