📌MemberServiceImpl.java
  
package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //⭐️수정⭐️

    public MemberServiceImpl(MemberRepository memberRepository) { //⭐️생성자 추가⭐️
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

✅ MemberServiceImpl은 MemoryMemberRepository를 의존하지 않음
   단지 MemberRepository 인터페이스만 의존함
   MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지는 알 수 없음
   MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 'AppConfig'에서 결정됨
   MemberServiceImpl은 이제부터 의존관게에 대한 고민은 외부에 맡기고 실행에만 집중하면 됨
