⛓️‍💥hello.hello_spring - repository - SpringDataJpaMemberRepository 인터페이스
📌SpringDataJpaMemberRepository.java
  
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//command + b : 해당 파일 소스 보기
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{ // 인터페이스가 인터페이스를 받을때는 extends 사용, 인터페이스는 다중상속 가능

    //JPQL select m from Member where m.name = ?
    @Override
    Optional<Member> findByName(String name); //✅name 과 같은 변수는 공통화 불가능하기 때문에 findByName 사용
}

