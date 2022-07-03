package personal.margin.wtf2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.margin.wtf2.domain.Reply;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }

    public void delete(Reply reply) {
        em.remove(reply);
    }

    public Reply findOne(Long id) {
        return em.find(Reply.class, id);
    }

    public List<Reply> findAll() {
        return em.createQuery("select r from Reply r")
                .getResultList();
    }

    public List<Reply> findAllByBoard(Long boardId) {
        return em.createQuery("select r from Reply r where r.board.id = :boardId")
                .setParameter("boardId", boardId)
                .getResultList();
    }
}
