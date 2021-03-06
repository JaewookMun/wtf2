package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Board;
import personal.margin.wtf2.domain.Employee;
import personal.margin.wtf2.domain.File;
import personal.margin.wtf2.domain.Reply;
import personal.margin.wtf2.repository.BoardRepository;
import personal.margin.wtf2.repository.EmployeeRepository;
import personal.margin.wtf2.repository.FileRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Long post(Board board, List<File> files) {
        board.setCreatedDate(LocalDateTime.now());
        for (File file : files) {
            board.addFile(file);
        }

        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public Long updateBoard(Long id, Board board) {
        Board findBoard = boardRepository.findOne(id);

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        findBoard.setModifiedDate(LocalDateTime.now());

        updateFiles(id, board.getFiles());

        return id;
    }

    @Transactional
    public void updateFiles(Long boardId, List<File> newFiles) {
        List<File> attachedFiles = fileRepository.findAllByBoard(boardId);

        for (File attached : attachedFiles) {
            boolean attachedExistenceFlag = false;

            for (File file : newFiles) {
                if(file.getId() == null) {
                    fileRepository.save(file);
                }
                else if(file.getId() == attached.getId()) {
                    attached.setName(file.getName());
                    attached.setPath(file.getPath());
                    attachedExistenceFlag = true;
                }
            }
            if(!attachedExistenceFlag) fileRepository.delete(attached);
        }
    }

    @Transactional
    public Long addReply(Long boardId, Long employeeId, Reply reply) {
        Board board = boardRepository.findOne(boardId);
        Employee replier = employeeRepository.findOne(employeeId);

        reply.setWriter(replier);

        board.addReply(reply);

        return reply.getId();
    }

    /*
        todo
     */
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }
}
