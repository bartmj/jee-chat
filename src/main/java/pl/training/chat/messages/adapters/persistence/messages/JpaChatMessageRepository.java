package pl.training.chat.messages.adapters.persistence.messages;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaChatMessageRepository {

    @Setter
    @PersistenceContext
    EntityManager entityManager;

    public void save(ChatMessageEntity chatMessageEntity) {
        entityManager.persist(chatMessageEntity);
    }
}
