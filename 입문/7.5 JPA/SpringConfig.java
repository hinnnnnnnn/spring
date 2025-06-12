⚙️ JPA를 사용하도록 스프링 설정 변경
    
@Configuration
public class SpringConfig {

    ⭐️추가⭐️
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    ⭐️추가 끝⭐️

//    private DataSource dataSource; 주석처리
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new jdbcTemplateMemberRepository(dataSource)
        return new JpaMemberRepository(em); ⭐️추가⭐️
    }
}
