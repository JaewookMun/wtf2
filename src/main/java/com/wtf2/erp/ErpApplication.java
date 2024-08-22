package com.wtf2.erp;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.repository.BoardRepository;
import com.wtf2.erp.board.repository.PageContentRepository;
import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.service.CompanyService;
import com.wtf2.erp.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class ErpApplication {

	// TODO : TEMP 추후 삭제
	private final CompanyService companyService;
	private final UserService userService;
	private final BoardRepository boardRepository;
	private final PageContentRepository pageContentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

	@PostConstruct
	private void init() {
		Long companyId = companyService.register("WTF2", LocalDateTime.now().toString(), "Jaewook");
		userService.register(companyId, "tester", "test", "123");

		Company company = companyService.findBy(companyId);
		Board notice = new Board("공지사항1", BoardType.NOTICE, company);
		notice.addText("안녕하세요!. \n반갑습니다.");
		boardRepository.save(notice);

		Board memo = new Board("MEMO", BoardType.PAGE, company);
		memo.addText("");
		boardRepository.save(memo);

		Board parent = new Board("워크스페이스", BoardType.PAGE, company);
		Board a = new Board("A", BoardType.PAGE, company);
		Board b = new Board("B", BoardType.PAGE, company);
		Board c = new Board("C", BoardType.PAGE, company);

		parent.addText("워크스페이스 입니다.");
		a.addText("");
		b.addText("");
		c.addText("");

		parent.addChild(a);
		parent.addChild(b);
		parent.addChild(c);
		boardRepository.save(parent);
	}
}
