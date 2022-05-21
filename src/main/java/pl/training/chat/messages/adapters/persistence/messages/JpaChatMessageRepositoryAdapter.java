package pl.training.chat.messages.adapters.persistence.messages;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
public class JpaChatMessageRepositoryAdapter implements MessageRepository {

    @Inject
    @Setter
    private JpaChatMessageRepository jpaMessageRepository;
    @Inject
    @Setter
    private JpaChatMessageMapper jpaChatMessageMapper;

    @Override
    public void send(ChatMessage chatMessage) {
        var chatMessageEntity = jpaChatMessageMapper.toEntity(chatMessage);
        jpaMessageRepository.save(chatMessageEntity);
    }
}
