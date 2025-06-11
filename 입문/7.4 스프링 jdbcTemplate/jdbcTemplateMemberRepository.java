순수 Jdbc와 동일한 환경설정
스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거, 하지만 SQL은 직접 작성
    
⛓️‍💥 hello.hello_spring - repository - jdbcTemplateMemberRepository
📌 jdbcTemplateMemberRepository.java
    
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class jdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired //스프링이 DataSource 객체를 자동 주입
    public jdbcTemplateMemberRepository(DataSource dataSource) { //JdbcTemplate는 DB와 연결된 DataSource를 기반으로 동작
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) { //MemberRepository 인터페이스의 save 메서드를 구현
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); //SimpleJdbcInsert: 복잡한 SQL 없이 INSERT 쿼리를 쉽게 작성하도록 도와주는 스프링 클래스
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); //"member" 테이블에 insert, "id"는 DB에서 자동 생성되는 키

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName()); //member 객체의 이름을 꺼내서, DB에 넣을 "name" 컬럼의 값으로 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); //DB에서 생성된 id 값을 Number 타입으로 받아옴
        member.setId(key.longValue()); //방금 insert하면서 받아온 id 값을 member 객체에 설정
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //id로 멤버를 조회하는 메서드
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id); //? 자리에 id 값, 결과는 memberRowMapper()를 통해 Member 객체로 변환
        return result.stream().findAny(); //결과는 리스트로 리턴됨 (조회 결과가 0개일 수도 있으니까)
    }

    @Override
    public Optional<Member> findByName(String name) { //이름으로 멤버를 조회
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //모든 멤버를 가져오는 쿼리
        return jdbcTemplate.query("select * from member", memberRowMapper()); //조건 없이 SELECT * 실행하고, 결과를 Member 객체 리스트로 변환해서 리턴
    }

    private RowMapper<Member> memberRowMapper() { //쿼리 결과(ResultSet)를 Member 객체로 변환하는 함수
        return (rs, rowNum) -> { //각 행(row)에서 id와 name 값을 꺼내서 Member 객체에 채워넣음
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}

/*
  return new RowMapper<Member>() { 옵션 + 엔터 : 람다식으로 변경 가능
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        }
 */


MemberServiceIntegrationTest에서 실행
