package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Dept;
import personal.margin.wtf2.repository.DeptRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;

    @Transactional
    public Long saveDept(String name, Long parentId) {

        Dept dept = null;

        if(parentId == null) {
            dept = Dept.createRootDept(name);
        } else {
            Dept parent = deptRepository.findOne(parentId);
            dept = Dept.createDept(name, parent);
        }

        deptRepository.save(dept);
        return dept.getId();
    }

    @Transactional
    public Long updateDept(Long id, Dept dept) {
        Dept findOne = deptRepository.findOne(id);

        findOne.setName(dept.getName());
        findOne.setDisplayOrder(dept.getDisplayOrder());
        findOne.setParent(dept.getParent());
        findOne.setChildList(dept.getChildList());

        return id;
    }

    public List<Dept> findByName(String name) {
        return deptRepository.findByName(name);
    }

    public List<Dept> findAllDepts() {
        return deptRepository.findAll();
    }


}
