package com.wtf2.erp;

import com.wtf2.erp.company.service.CompanyService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ErpApplication {

	// TODO : TEMP 추후 삭제
	@Autowired
	private CompanyService companyService;


	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}


	@PostConstruct
	private void init() {
		companyService.register("WTF2", LocalDateTime.now().toString(), "Jaewook");

	}
}
