package com.wtf2.erp.company.domain;

import com.wtf2.erp.dept.domain.Dept;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return getId().equals(company.getId()) && Objects.equals(getName(), company.getName()) && getGuid().equals(company.getGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGuid());
    }
}
