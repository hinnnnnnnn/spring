⛓️‍💥hello.core - member - MemberRepository (인터페이스 생성)
📌MemberRepository.java
  
package hello.core.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
