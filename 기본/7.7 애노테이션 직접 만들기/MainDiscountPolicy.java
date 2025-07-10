⛓️‍💥hello.core - annotation - MainDiscountPolicy (인터페이스 생성)
📌MainDiscountPolicy.java
  
package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//⭐️Qualifier.java 파일에서 가져오기 
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy") 
public @interface MainDiscountPolicy {
}
