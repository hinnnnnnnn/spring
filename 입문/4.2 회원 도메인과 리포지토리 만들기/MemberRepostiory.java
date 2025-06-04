hello.hello_spring - repository - MemberRopsitory(interface)

package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장, 회원 객체를 DB나 메모리에 저장할 때 사용
    Optional<Member> findById(Long id); //ID로 회원을 찾기, Optional은 값이 없을 수도 있다는 걸 나타내는 안전한 래퍼 클래스
    Optional<Member> findByName(String name); //이름으로 회원을 찾기, 중복 가입 여부 확인 시 사용
    List<Member> findAll(); //모든 회원 리스트를 반환
}


🔧 인터페이스란?
   interface는 기능(메서드)의 틀만 정의하는 구조
   실제 동작(구현)은 다른 클래스에서
   예: "이런 기능을 제공해야 해!" 라고 약속만 해두는 것

🔄 나중에 구현 클래스에서 이 인터페이스를 구현함
   코드 구조가 유연하고 변경에 강한 구조가 됨 (예: DB에서 메모리로 바꾸거나, 반대로 바꿀 수 있음)

✅ Optional = null일 수도 있는 값을 안전하게 다루는 상자 📦
