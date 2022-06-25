package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ApprovalDocument {

    @Id @GeneratedValue
    @Column(name = "approval_document_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private ProcessStatus status;

    private String comment;
    private LocalDateTime processedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_line_id")
    private ApprovalLine approvalLine;
}
