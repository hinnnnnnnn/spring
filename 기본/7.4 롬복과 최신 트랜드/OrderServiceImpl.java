📌OrderServiceImpl.java 수정

@Component
@RequiredArgsConstructor // 생성자가 자동으로 들어감 (내부에 존재)
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    } -----> ⭐️@RequiredArgsConstructor만 추가하면 삭제해도됨 (final이 붙은 필드를 모아서 생성자를 자동으로 만들어줌)⭐️
  
}


lombok 라이브러리의 @RequiredArgsConstructor 함께 사용하면 기능은 다 제공하면서, 코드는 깔끔하게 사용 가능함
