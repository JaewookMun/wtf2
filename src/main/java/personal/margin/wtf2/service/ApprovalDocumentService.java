package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.ApprovalDocument;
import personal.margin.wtf2.domain.ProcessStatus;
import personal.margin.wtf2.repository.ApprovalDocumentRepository;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApprovalDocumentService {

    private final ApprovalDocumentRepository documentRepository;

    @Transactional
    public Long create(ApprovalDocument document) {
        document.setCreatedDate(LocalDateTime.now());
        documentRepository.save(document);

        return document.getId();
    }

    @Transactional
    public Long update(Long id, ApprovalDocument document) {
        ApprovalDocument foundDocument = documentRepository.findOne(id);

        if(foundDocument.getStatus().equals(ProcessStatus.IN_PROGRESS))
            throw new IllegalStateException("결재가 진행 중인 문서는 문서내용을 수정할 수 없습니다.");

        foundDocument.setDocumentType(document.getDocumentType());
        foundDocument.setTitle(document.getTitle());
        foundDocument.setContent(document.getContent());
        foundDocument.setApprovalLine(document.getApprovalLine());

        return id;
    }

    @Transactional
    public Long proceedDocument(Long approvalDocumentId, boolean approvalFlag, String comment) {
        ApprovalDocument foundDocument = documentRepository.findOne(approvalDocumentId);

//        foundDocument.proceed

        if(approvalFlag) foundDocument.setStatus(ProcessStatus.APPROVED);
        else foundDocument.setStatus(ProcessStatus.REJECTED);

        foundDocument.addComent(comment);

        return approvalDocumentId;
    }


}

