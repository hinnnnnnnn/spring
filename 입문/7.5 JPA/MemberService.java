🛠️ 서비스 계층에 트랜잭션 추가

@Transactional //추가
public class MemberService {


• `org.springframework.transaction.annotation.Transactional` 를 사용
• 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋, 만약 런타임 예외 발생시 롤백
• JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 함
