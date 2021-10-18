package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

// Component Scan이 되기 위함
@Repository
public class MemberJpaRepository {
    // JPA가 알아서 EntityManager를 삽입해줌.
    @PersistenceContext
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public void delete(Member member){
        em.remove(member);
    }

    public List<Member> findAll(){
        //전체 조회는 JPQL(Table이 아닌 객체를 대상으로 하는 쿼리로서 SQL과 비슷) 사용해야 함.
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();

    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findByUsername(String username){
        return em.createQuery("Member.findByUsername", Member.class).setParameter("username", username).getResultList();
    }
}
