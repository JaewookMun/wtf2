package personal.margin.wtf2.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Board;
import personal.margin.wtf2.domain.File;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EntityManager em;

    @Test
    @Rollback(value = false)
    public void tdd() throws Exception {
        //given
        File file = new File();
        file.setName("test");
        file.setPath("C:/disk");

        Board board = new Board();
        board.setTitle("example");
        board.addFile(file);

        //when
        boardRepository.save(board);
        fileRepository.save(file);

        em.flush();

        fileRepository.findAllByBoard(board);

        //then
    }
}