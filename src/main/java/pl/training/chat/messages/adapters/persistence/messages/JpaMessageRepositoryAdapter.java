package pl.training.chat.messages.adapters.persistence.messages;

import lombok.RequiredArgsConstructor;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageRepository;

import jakarta.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaMessageRepositoryAdapter implements MessageRepository {

    private JpaMessageRepository jpaMessageRepository;

    @Override
    public void send(ChatMessage chatMessage) {
        jpaMessageRepository.save(chatMessage);
    }
}
