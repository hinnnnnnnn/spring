⛓️‍💥hello.core - AutoAppConfig
📌AutoAppConfig.java
  
package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}

//컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에, AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고 실행 되어 버림
//그래서 excludeFilters를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외
//기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택

//컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록함
//각 클래스가 컴포넌트 스캔의 대상이 되도록 @Component 애노테이션을 붙여줌
