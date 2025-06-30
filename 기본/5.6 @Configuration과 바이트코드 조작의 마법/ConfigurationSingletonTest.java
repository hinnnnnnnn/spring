📌ConfigurationSingletonTest.java에 추가
  
 @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}

/* 출력 결과
bean = class hello.core.AppConfig$$SpringCGLIB$$0
*/

⭐️ 스프링이 CGLIB 이라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스로 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것
