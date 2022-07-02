package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Dept;
import personal.margin.wtf2.repository.DeptRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeptService {

    private final DeptRepository deptRepository;

    @Transactional
    public Long createDept(Dept dept) {
        deptRepository.save(dept);
        return dept.getId();
    }

    @Transactional
    public Long updateDept(Long id, Dept dept) {
        Dept findOne = deptRepository.findOne(id);

        findOne.setName(dept.getName());
        findOne.setDisplayOrder(dept.getDisplayOrder());
        findOne.setParent(dept.getParent());
        findOne.setChildren(dept.getChildren());

        return id;
    }

    public List<Dept> findByName(String name) {
        return deptRepository.findByName(name);
    }

    public List<Dept> findAllDepts() {
        return deptRepository.findAll();
    }


}
