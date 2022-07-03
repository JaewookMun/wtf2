package personal.margin.wtf2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.margin.wtf2.domain.ApprovalDocument;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovalDocumentRepository {

    private final EntityManager em;

    public void save(ApprovalDocument document) {
        em.persist(document);
    }

    public ApprovalDocument findOne(Long id) {
        return em.find(ApprovalDocument.class, id);
    }

    public void delete(ApprovalDocument document) {
        em.remove(document);
    }

    public List<ApprovalDocument> findAll() {
        return em.createQuery("select a from ApprovalDocument a")
                .getResultList();
    }
}
