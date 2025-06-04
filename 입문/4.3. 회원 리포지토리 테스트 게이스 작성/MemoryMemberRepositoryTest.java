test - hello.hello-spring - repository - MemoryMemberRepositoryTest
  
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; //📌 option + enter 처리해서 Assertions 생략

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository(); // ✅ 저장소 인스턴스 생성

    @AfterEach //✅ 테스트 끝날 때마다 실행, 각 테스트가 끝날 때마다 저장소를 비워주기
    public void afterEach(){ //저장소 비우기
        repository.clearStore();
    }

    @Test 
    public void save(){ 
        Member member = new Member(); //새로운 회원 member를 저장
        member.setName("spring");
        repository.save(member);
      
        //저장한 ID로 다시 찾아서 잘 저장됐는지 검증
        Member result = repository.findById(member.getId()).get();
        //case1
        //System.out.println("result = " + (result == member)); //result = true
        //case2
        //Assertions.assertEquals( member, result); //출력되는 값은 없지만 잘못된 값이 들어오면 오류 출력 (Assertions : JUnit)
        //case3
        assertThat(member).isEqualTo(result); //📌 option + enter 처리해서 Assertions 생략 (Assertions : assertj) 
    }

    @Test
    public void findByName() { //회원 2명을 저장하고, 이름으로 검색
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //spring1이라는 이름을 가진 회원이 member1과 같은지 확인
        assertThat(result).isEqualTo(member1);

    } //💡 shift + f + 6 : 리네임 가능

    @Test
    public void findAll() { //회원 2명을 저장한 뒤, 전체 목록을 가져와서 크기가 2인지 확인
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

🔍 팁
📌 assertThat() 
   AssertJ 라이브러리의 메서드
   테스트 결과가 예상값과 일치하는지 비교해주는 도구

📌 .get() 
   Optional<Member>에서 값을 꺼낼 때 사용
   보통은 isPresent()나 ifPresent()로 null 체크도 같이 해주는 게 좋음

⛓️‍💥 결과
• 전체 실행 - 오류 발생
• findAll() 실행 -> 이미 spring1, spring2 값이 저장됨
• 따라서 test하나가 끝나면 clear처리를 해줘야함
