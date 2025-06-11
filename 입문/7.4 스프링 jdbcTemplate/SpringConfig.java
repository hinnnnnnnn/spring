⭐️JdbcTemplate을 사용하도록 스프링 설정 변경

package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.repository.jdbcTemplateMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    //✅추가 및 주석 처리
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new jdbcTemplateMemberRepository(dataSource);
    }
}
