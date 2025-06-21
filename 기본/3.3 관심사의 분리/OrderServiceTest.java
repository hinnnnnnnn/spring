📌OrderServiceTest.java

public class OrderServiceTest {

    //⭐️추가⭐️
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
      
    }

