⛓️‍💥test - singleton - SingletonTest
📌SingletonTest.java (스프링 컨테이너와 싱글톤)
  
  @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);;

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 =/= memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }

/* 출력 결과
memberService1 = hello.core.member.MemberServiceImpl@48a12036
memberService2 = hello.core.member.MemberServiceImpl@48a12036
 */
