package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 연관관계 필드(team)은 ToString에 넣으면 무환루프 에러
@ToString(of = {"id", "username", "age"})
// App loading 시점에 파싱해봐서 문법 오류 체크 (장점). Jpql을 sql로 미리 만들어 봄.
@NamedQuery(
        name="Member.findByUsername",
        //JPQL
        query="select m from Member m where m.username= :username"
)
public class Member {
    @Id
    @GeneratedValue // sequence
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    // 왠만하면 LAZY로 setting 필요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    //JPA Entity는 기본적으로 기본 생성자 하나 필요, 접근제어자도 public, protected로

    public Member(String username){
        this.username=username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username=username;
        this.age=age;
        if(team!=null){
            changeTeam(team);
        }
    }

    public void changeUsername(String username){
        this.username=username;
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }
}
