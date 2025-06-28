⛓️‍💥test - singleton - SingletonTest
📌SingletonTest.java에 추가
  
  @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

/* 출력 결과
singletonService1 = hello.core.singleton.SingletonService@ba2f4ec
singletonService2 = hello.core.singleton.SingletonService@ba2f4ec
 */
