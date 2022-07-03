package personal.margin.wtf2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.margin.wtf2.domain.Board;
import personal.margin.wtf2.domain.File;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public void save(File file) {
        em.persist(file);
    }

    public void delete(File file) {
        em.remove(file);
    }

    public File findOne(Long id) {
        return em.find(File.class, id);
    }

    public List<File> findAllByBoard(Long boardId) {
        return em.createQuery("select f from File f where f.board.id = :boardId", File.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

}
