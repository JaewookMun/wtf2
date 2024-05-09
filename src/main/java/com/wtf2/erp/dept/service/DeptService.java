package com.wtf2.erp.dept.service;

import com.wtf2.erp.dept.domain.Dept;
import com.wtf2.erp.dept.dto.DeptCreateDto;
import com.wtf2.erp.dept.dto.DeptEditDto;
import com.wtf2.erp.dept.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;

    @Transactional
    public Long create(DeptCreateDto deptCreateDto) {
        Dept parentDept = deptRepository.findById(deptCreateDto.getParentId())
                .orElseThrow(() -> new IllegalArgumentException("parentId can not be null"));

        return deptRepository.save(new Dept(deptCreateDto.getName(), parentDept)).getId();
    }

    @Transactional
    public Long edit(Long deptId, DeptEditDto deptEditDto) {
        Dept dept = deptRepository.findById(deptId).get();

        if (!dept.getName().equals(deptEditDto.getName())) {
            dept.changeName(deptEditDto.getName());
        } else if (!dept.getParent().getId().equals(deptEditDto.getParentId())) {
            dept.changeParent(deptRepository.findById(deptEditDto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("")));
        }

        return dept.getId();
    }

}
