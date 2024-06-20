package com.wtf2.erp.company.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company {

    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String crno;

    public Company(String name) {
        this.name = name;
    }
}
