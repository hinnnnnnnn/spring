📌MemberServiceTest.java

public class MemberServiceTest {
  
    //⭐️추가⭐️
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
      
    }
