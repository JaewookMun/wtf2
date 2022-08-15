package personal.margin.wtf2.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.repository.DeptRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DeptServiceTest {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptRepository deptRepository;

    @DisplayName("ROOT 부서 생성")
//    @Rollback(value = false)
    @Test
    public void createRootDept() throws Exception {
        //given
        String name = "wtf";
        Long parentId = null;

        //when
        Long deptId = deptService.saveDept(name, parentId);

        //then
        assertEquals(name, deptRepository.findOne(deptId).getName());
    }

    @DisplayName("하위 부서 생성")
    @Test
    public void createSubDept() throws Exception {
        //given
        //root
        String rootName = "wtf";
        Long rootParentId = null;
        Long rootId = deptService.saveDept(rootName, rootParentId);

        // sub
        String name = "sales";
        Long parentId = rootId;

        //when
        Long deptId = deptService.saveDept(name, parentId);

        //then
        assertEquals(name, deptRepository.findOne(deptId).getName());
    }
}