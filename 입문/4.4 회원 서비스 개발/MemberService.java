hello.hello_spring - service (package) - MemberService.java

package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    /*private final MemberRepository memberRepository = new MemoryMemberRepository();*/

    //✅ 메모리 저장소를 직접 생성하는 것이 아니라, 외부에서 생성한 같은 인스턴스를 받도록 변경 (테스트 용이성 ↑, 유지보수성 ↑)
    private final MemberRepository memberRepository;

    //✅ 생성자를 통해 MemberRepository 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    //📌회원가입
    public Long join(Member member) {

        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member); //저장소에 회원 저장
        return member.getId(); //저장 후 회원 id 반환
    }

    //📌중복 회원 검사 (같은 이름의 회원이 이미 존재하면 예외 발생)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //📌전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //📌회원 id로 회원 한 명 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}


//단축키 control + T -> 메서드 생성 control + command + option
//생성자 부분에 command + shift + T : 새 테스트 생성 (멤버 전체 체크 후 생성 하면 자동으로 test가 생성됨)


⭐️ 중복 회원 검증과 회원 가입 처리 과정의 두가지 코드 방식
case1 - 직접 중복 검사 처리 
 Optional<Member> result = memberRepository.findByName(member.getName()); //저장소에서 이름이 같은 회원이 있는지 검색
 result.ifPresent(m -> { //만약 해당 이름을 가진 회원이 존재하면 예외 발생
 throw new IllegalStateException("이미 존재하는 회원입니다.");
 });
✅ 이 방식은 간단하지만, 중복 검사 로직이 join() 메서드에 직접 들어가 있어 가독성이 떨어지고 재사용이 어려움

case2 - 별도 메서드로 분리한 방식
 validateDuplicateMember(member); //중복 검사 메서드 호출, 중복 회원 검사 로직을 별도의 메서드로 분리하여 호출
 memberRepository.save(member); //회원 저장, 중복이 없으면 저장 후 반환
 return member.getId(); //저장된 회원의 ID 반환
✅ 이 방식은 중복 검사 로직이 따로 분리되어 가독성, 유지보수성, 재사용성이 높아짐
특히 테스트나 추후 기능 확장 시에도 유리

