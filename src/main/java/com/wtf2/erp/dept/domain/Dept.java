package com.wtf2.erp.dept.domain;

import com.wtf2.erp.company.domain.Company;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Dept {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Dept parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "id")
    private List<Dept> children = new ArrayList<>();

    public Dept(String name, Dept parent) {
        this.name = name;
        this.parent = parent;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeParent(Dept parent) {
        this.parent = parent;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
