🔎자동 빈 등록 VS 자동 빈 등록
컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 스프링은 오류를 발생시킴
🚨'ConflictingBeanDefinitionException'예외 발생

📌AppConfig.java에 추가
  
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

⭐️ 이 경우 수동 빈 등록이 우선권을 가짐
(수동 빈이 자동 빈을 오버라이딩 해버림)
