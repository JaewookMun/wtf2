package com.wtf2.erp.company.domain;

import com.wtf2.erp.dept.domain.Dept;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;
    @Column(unique = true)
    private String guid;

    private String ceo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Dept> dept = new ArrayList<>();

    public Company(String name, String guid, String ceo) {
        this.name = name;
        this.guid = guid;
        this.ceo = ceo;
    }

    public void addDept(Dept dept) {
        this.dept.add(dept);
        dept.setCompany(this);
    }
}
