⛓️‍💥 MemberServiceTest 복사해서 만들기 
📌 MemberServiceIntegrationTest
    
package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //✅스프링 컨테이너와 테스트를 함께 실행
@Transactional //✅ 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백
                     이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않음

class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        /** JUnit에는 assertThat이 없으므로 Assertions에서 assertJ를 사용 **/
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); //
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}

//h2에서 delete from member 후 실행
