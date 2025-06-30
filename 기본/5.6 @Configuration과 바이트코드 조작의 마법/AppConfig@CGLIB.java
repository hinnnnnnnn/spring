AppConfig@CGLIB 예상 코드

  @Bean
  public MemberRepository memberRepository() {
  if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
    return 스프링 컨테이너에서 찾아서 반환;
  } else { //스프링 컨테이너에 없으면
      기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
      return 반환
        }
}

@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어짐
⭐️덕분에 싱글톤 보장⭐️
