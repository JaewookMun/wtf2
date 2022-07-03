package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Reply;
import personal.margin.wtf2.repository.ReplyRepository;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public Long post(Reply reply) {
        reply.setCreatedDate(LocalDateTime.now());
        replyRepository.save(reply);

        return reply.getId();
    }

    @Transactional
    public Long update(Long id, Reply reply) {
        Reply findReply = replyRepository.findOne(id);

        findReply.setContent(reply.getContent());
        findReply.setModifiedDate(LocalDateTime.now());

        return id;
    }

    @Transactional
    public void remove(Long id) {
        Reply reply = replyRepository.findOne(id);
        replyRepository.delete(reply);
    }
}
