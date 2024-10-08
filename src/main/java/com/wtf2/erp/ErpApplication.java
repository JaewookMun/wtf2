package com.wtf2.erp;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.repository.BoardRepository;
import com.wtf2.erp.board.repository.PageContentRepository;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.service.GroupService;
import com.wtf2.erp.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ErpApplication {

	// TODO : TEMP 추후 삭제
	private final GroupService groupService;
	private final UserService userService;
	private final BoardRepository boardRepository;
	private final PageContentRepository pageContentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

	@PostConstruct
	private void init() {
		String loginId = "test";
		userService.register("tester", loginId, "123");
		Long groupId = groupService.register("WTF2 - ErpApplication", loginId);

		Group group = groupService.getGroupById(groupId);
		Board notice = new Board("공지사항1", BoardType.NOTICE, group);
		notice.addText("안녕하세요!. \n반갑습니다.");
		boardRepository.save(notice);

		Board memo = new Board("MEMO", BoardType.PAGE, group);
		memo.addText("");
		boardRepository.save(memo);

		Board parent = new Board("워크스페이스", BoardType.PAGE, group);
		Board a = new Board("A", BoardType.PAGE, group);
		Board b = new Board("B", BoardType.PAGE, group);
		Board c = new Board("C", BoardType.PAGE, group);

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
