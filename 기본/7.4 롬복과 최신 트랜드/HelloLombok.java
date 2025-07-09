⛓️‍💥hello.core - HelloLombok
📌HelloLombok.java
  
package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter //getter, setter 들어가있음
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("dfdsf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}

/* 출력 결과
name = dfdsf
 */
