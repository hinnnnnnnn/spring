hello.hello_spring - service (package) - MemberService.java

package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    /*private final MemberRepository memberRepository = new MemoryMemberRepository();*/

    //같은 인스턴스를 사용하도록 변경
    private final MemberRepository memberRepository;
    
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    //회원가입
    public Long join(Member member) {

        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}


//단축키 control + T -> 메서드 생성 control + command + option
//생성자 부분에 command + shift + T : 새 테스트 생성 (멤버 전체 체크 후 생성 하면 자동으로 test가 생성됨)
//같은 이름이 있는 중복 회원X
/**case1.
 Optional<Member> result = memberRepository.findByName(member.getName());
 result.ifPresent(m -> {
 throw new IllegalStateException("이미 존재하는 회원입니다.");
 }); **/

/**case2
 validateDuplicateMember(member);
 memberRepository.save(member);
 return member.getId(); **/
