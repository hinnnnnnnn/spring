hello.hello_spring - domain - Member

package hello.hello_spring.domain;

public class Member {

    private Long id; //회원 고유번호, DB에서 자동 생성되는 ID 등으로 사용
    private String name; //이름

    //🛠️ Getter/Setter 메서드 : 필드 값에 접근하거나 수정할 수 있는 방법을 제공
    public Long getId() { //ID 값을 가져오는 메서드
        return id;
    }

    public void setId(Long id) { //ID 값을 설정하는 메서드
        this.id = id;
    }

    public String getName() { //이름 값을 가져오는 메서드
        return name;
    }

    public void setName(String name) { //이름 값을 설정하는 메서드
        this.name = name;
    }
}
