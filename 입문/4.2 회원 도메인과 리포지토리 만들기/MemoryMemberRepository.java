hello.hello_spring - repository - MemoryMemberRepository.java

package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //회원 정보를 메모리에 저장하는 Map. (id → Member)
    private static long sequence = 0L; //회원 ID를 1씩 증가시켜주는 역할 (고유 ID 생성기)

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //ID를 자동으로 생성해서 member에 설정
        store.put(member.getId(), member); //store에 저장 (Map 형태: ID → Member)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //id로 회원을 찾음
        return Optional.ofNullable(store.get(id)); //null이 반환된 가능성이 있으면 Optional.ofNullablef로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) { //모든 회원 리스트에서 name이 같은 회원을 찾음
        return store.values().stream() //stream() + filter() + findAny() 사용 → 람다식!
                .filter(member -> member.getName().equals(name))
                .findAny(); //"member 객체 중에서 이름이 같은 애를 찾아라!"
    }

    @Override
    public List<Member> findAll() { //저장된 모든 회원을 리스트로 반환
        return new ArrayList<>(store.values());
    }
}

//option + enter : 메모리 구현, import
//잘 돌아가는지 확인하기 위해서는 test case 작성

📦 HashMap
Key(키) 와 Value(값) 를 쌍으로 저장하는 자료구조
예: "학생번호 → 이름", "ID → 회원 객체" 이런 식으로 연결함.
  ** Long(회원 ID)을 키로, Member 객체를 값으로 저장하는 맵
    store.put(member.getId(), member); // 저장
    store.get(id); // ID로 회원 찾기

📦 람다식
간단한 함수 표현 방식
예:
📍 일반적인 방식
for (Member m : members) {
    if (m.getName().equals("홍길동")) {
        return m;
    }
}
⬇️ 람다식으로 바꾸면?
members.stream()
       .filter(m -> m.getName().equals("홍길동"))
       .findAny();
