package personal.margin.wtf2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Dept;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.setTempDept();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final EntityManager em;

        public void setTempDept() {
            Dept rootDept = Dept.createRootDept("wtf");
            em.persist(rootDept);

        }
    }
}
