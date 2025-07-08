📌OrderServiceImpl.java 수정
🔎final 키워드
    
@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
    }
}

필수 필드인 discountPolicy에 값을 설정해야 하는데 누락 됨
자바는 컴파일 시점에 다음 오류를 발생 시킴
🚨 java : variable discountPolicy might not have been initalized
