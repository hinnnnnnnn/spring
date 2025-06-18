⛓️‍💥hello.core - member - MemoryMemberRepository (클래스 생성)
📌MemoryMemberRepository.java
  
package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override //option + enter : 메소드 구현
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
