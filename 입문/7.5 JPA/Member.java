⛓️‍💥JPA 엔티티 매핑
@Entity ⭐️추가⭐️
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) ⭐️추가⭐️
    private Long id; //식별자
