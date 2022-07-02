package personal.margin.wtf2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.margin.wtf2.domain.Dept;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeptRepository {

    private final EntityManager em;

    public void save(Dept dept) {
        em.persist(dept);
    }

    public Dept findOne(Long id) {
        return em.find(Dept.class, id);
    }

    public void delete(Dept dept) {
        em.remove(dept);
    }

    public List<Dept> findAll() {
        return em.createQuery("select d from Dept d", Dept.class)
                .getResultList();
    }

    public List<Dept> findByName(String name) {
        return em.createQuery("select d from Dept d where d.name = :name", Dept.class)
                .setParameter("name", name)
                .getResultList();
    }


}
