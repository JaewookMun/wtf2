package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Board;
import personal.margin.wtf2.domain.File;
import personal.margin.wtf2.repository.BoardRepository;
import personal.margin.wtf2.repository.FileRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Transactional
    public Long post(Board board, List<File> files) {
        board.setPostDate(LocalDateTime.now());
        for (File file : files) {
            board.addFile(file);
            fileRepository.save(file);
        }

        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public Long update(Long id, Board board) {
        Board findBoard = boardRepository.findOne(id);

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        findBoard.setModifiedDate(LocalDateTime.now());

        // todo
//        List<File> savedFileList = findBoard.getFiles();
//
//        for (File savedFile : savedFileList) {
//            for (File file : board.getFiles()) {
//                File one = fileRepository.findOne(file.getId());
//
//                if(savedFile.getId() == one.getId()) {
//
//                }
//
//
//            }
//        }


        return id;
    }

    /*
        todo
     */
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }
}
