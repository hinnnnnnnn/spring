💡 생성자 부분에 command + shift + T : 새 테스트 생성 (멤버 전체 체크 후 생성 하면 자동으로 test가 생성됨)

package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // assertThat()을 위해 사용
import static org.junit.jupiter.api.Assertions.assertThrows; // 예외 발생 테스트용

class MemberServiceTest {

    /* MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); */

    //📌테스트에서 사용할 서비스와 메모리 저장소 선언
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //📌각 테스트 실행 전에 실행됨 (공통 세팅)
    @BeforeEach
    public void beforeEach() {
        //✅ MemoryMemberRepository와 MemberService를 새로 생성하고 연결
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //📌각 테스트가 끝날 때마다 저장소 초기화
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    //📌회원가입 테스트
    @Test
    void 회원가입() {   //test는 한글로 작성해도 무방
        //✅given (준비)
        Member member = new Member();
        member.setName("hello");
        
        //✅when (실행)
        Long saveId = memberService.join(member);
        
        //✅then (검증)
        //⭐️ JUnit에는 assertThat이 없으므로 Assertions에서 assertJ를 사용 ⭐️
        Member findMember = memberService.findOne(saveId).get(); // 저장된 회원 이름과 조회한 이름이 같은지 확인
        assertThat(member.getName()).isEqualTo(findMember.getName()); 
    }

    //📌중복 회원 예외 처리 테스트
    @Test
    public void 중복_회원_예외() {
        //✅given (중복된 이름을 가진 회원 두 명 생성)
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //✅when (member1은 정상 가입)
        memberService.join(member1);
        // member2 가입 시 중복 예외 발생 예상
        // 단축키: ⌘ + ⌥ + V → 자동으로 예외 객체 받기
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 예외 메시지가 예상한 것과 같은지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //✅then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}

//control + R : 이전에 실행했던거 그대로 다시 실행

🔎 이 코드는 중복 회원 가입 시 예외가 발생하는지 확인하는 테스트 코드
JUnit의 assertThrows를 사용하지 않고, 전통적인 try-catch 방식으로 예외를 검증하는 예
    try {
    memberService.join(member2); // 중복 이름 회원 가입 시도 → 예외가 발생해야 함
    fail();                      // 예외가 발생하지 않으면 테스트 실패로 처리
}
      catch (IllegalStateException e) {
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
}
    
🆚 assertThrows()와의 비교
IllegalStateException e = assertThrows(
    IllegalStateException.class,
    () -> memberService.join(member2)
);
assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
