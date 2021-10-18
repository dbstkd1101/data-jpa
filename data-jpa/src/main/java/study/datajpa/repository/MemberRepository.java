package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;

// @Repository annotation 없어도 JpaRepository를 상속만 하면 알아챔
// Spring Data JPA
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Spring data Jpa가 제공하는 강력한 기능. 메소드 이름으로 쿼리 생성
    // 단점 메소드 명이 길어지면 답 없음
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // 주석처리 해도 정상 동작. 관례사 메소드 이름으로 List<T>의 T.메소드명으로 named Query를 먼저 찾음. if not, method 명으로 찾음
    //@Query(name = "Member.findByUsername")
    // 함수명은 아무거나 가능
    List<Member> findByUsername(@Param("username") String username);

    // 이름이 없는 named Query (활용 가장 多). 정적 쿼리
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findMember(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // 'new'는...마치 객체를 생성해서 반환하듯이...Jpql이 정한 문법
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);


    // 반환타입으로 collection일 경우, 0건 return 되어도 null이 아닌 것이 return 됨.
    // 반환타입으로 단일 객체/타입일 경우는 null
    // 반환 데이터가 있을 수도 없을 수도 있으면 Optional<Member>(단건) 사용하면 됨
    // 반환으로 Optional<Member> 사용할 경우, 반환 데이터가 2개 이상일 경우는 Exception 발생
}
