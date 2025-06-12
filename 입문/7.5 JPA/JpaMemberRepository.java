⛓️‍💥 hello.hello_spring - repository - JpaMemberRepository
📌 JpaMemberRepository.java
  
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //생성자에서 EntityManager를 주입받아 em이라는 이름의 멤버 변수에 저장

    public JpaMemberRepository(EntityManager em) { //EntityManager는 Spring이 @PersistenceContext 또는 @Transactional 환경에서 관리해주는 객체로, JPA의 핵심 기능 제공
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //전달된 member 객체를 데이터베이스에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //id를 기준으로 데이터베이스에서 Member 엔티티를 조회
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
      List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) //JPQL (Java Persistence Query Language)을 사용하여 name으로 검색
              .setParameter("name", name)
              .getResultList();

      return result.stream().findAny(); //결과는 리스트로 오지만, 하나만 필요하므로 stream().findAny()로 Optional로 변환
    }

    @Override
    public List<Member> findAll() {
        return  em.createQuery("select m from Member m", Member.class) //JPQL로 Member 테이블의 모든 데이터를 조회
                .getResultList();
    }
}

💡 참고: JPA vs SQL
JPA에서 사용하는 JPQL은 SQL과 비슷하지만, 테이블이 아닌 엔티티(Member)를 기준으로 쿼리함
-- SQL: 실제 테이블 이름과 컬럼 이름을 사용
SELECT * FROM member WHERE name = '홍길동';
-- JPQL: 엔티티와 필드 이름을 사용
SELECT m FROM Member m WHERE m.name = :name;
