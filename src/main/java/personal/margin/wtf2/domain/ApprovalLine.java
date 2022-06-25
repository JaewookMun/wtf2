package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ApprovalLine {

    @Id @GeneratedValue
    @Column(name = "approval_line_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_authorizer_id")
    private Employee firstAuthorizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_authorizer_id")
    private Employee secondAuthorizer;
}
