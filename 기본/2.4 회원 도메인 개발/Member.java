⛓️‍💥hello.core - member - Member(클래스 생성)
📌Member.java
  
package hello.core.member;

public class Member {
    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) { //✅control + enter : 생성자 자동 생성
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() { //✅control + enter : getter setter 자동 생성
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
