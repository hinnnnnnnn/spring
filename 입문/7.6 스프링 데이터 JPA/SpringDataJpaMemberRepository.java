hello.hello_spring - repository - SpringDataJpaMemberRepository인터페이스
SpringDataJpaMemberRepository.java
  
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//command + b : 해당 파일 소스 보기
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{ // 인터페이스가 인터페이스를 받을때는 extends 사용, 인터페이스는 다중상속 가능

    //JPQL select m from Member where m.name = ?
    @Override
    Optional<Member> findByName(String name); //name 과 같은 변수는 공통화 불가능 하기 때문에 findByName 사용
}


//총 정리
//순수 Jdbc : 쿼리 양이 어마어마함
//스프링 통합 테스트 : Jdbc로 통합 테스트 만들기
//스프링 JdbcTemplate : 반복되는 코드는 줄어들지만 sql은 직접 작성
//JPA: 기본적인 CRUD는 하지만 쿼리는 작성할 필요 없음
//스프링 JPA: 구현 클래스를 작성할 필요없이 인터페이스만으로 작성 끝
