package pl.training.chat.messages.adapters.persistence.messages;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatMessage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaMessageRepository {

    @Setter
    @PersistenceContext
    EntityManager entityManager;

    public void save(ChatMessage chatMessage) {
        entityManager.persist(chatMessage);
    }
}
