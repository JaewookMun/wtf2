package com.wtf2.erp.dept.repository;

import com.wtf2.erp.dept.domain.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DeptRepositoryTest {

    @Autowired
    DeptRepository deptRepository;

    @Test
    void test() {
        Dept test = new Dept("test", null);
        deptRepository.save(test);
    }

}