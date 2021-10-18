package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

// @Repository annotation 없어도 JpaRepository를 상속만 하면 알아챔
// <?, ?>에서 두 번재는 Entity의 PK로 매핑된 type
public interface TeamRepository extends JpaRepository<Team, Long> {

}
