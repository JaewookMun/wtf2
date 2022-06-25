package personal.margin.wtf2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.margin.wtf2.domain.Employee;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EntityManager em;

    public void save(Employee employee) {
        em.persist(employee);
    }

    public void findOne(Long id) {
        em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    public List<Employee> findByName(String name) {
        return em.createQuery("select e from Employee e where e.name = :name", Employee.class)
                .setParameter("name", name)
                .getResultList();
    }
}
