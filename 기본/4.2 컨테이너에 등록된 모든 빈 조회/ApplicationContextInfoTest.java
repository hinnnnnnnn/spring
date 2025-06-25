⛓️‍💥test - beanfing - ApplicationContextInfoTest
📌ApplicationContextInfoTest.java
  
package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //🔎ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름 조회
        for (String beanDefinitionName : beanDefinitionNames) { //✅iter + tap : for문 자동 생성
            Object bean = ac.getBean(beanDefinitionName); //🔎ac.getBean() : 빈 이름으로 빈 객체를 조회
            System.out.println("name = " + beanDefinitionName + "object = " + bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //iter + tap : for문 자동 생성
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //💡ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //💡ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { //🔎getRole() : 스프링 내부에서 사용하는 빈 구분
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
        }
    }
}
