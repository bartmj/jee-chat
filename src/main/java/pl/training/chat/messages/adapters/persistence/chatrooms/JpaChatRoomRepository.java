package pl.training.chat.messages.adapters.persistence.chatrooms;

import lombok.Setter;
import pl.training.chat.messages.adapters.persistence.messages.ChatMessageEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class JpaChatRoomRepository {

    @Setter
    @PersistenceContext
    EntityManager entityManager;

    public void save(ChatRoomEntity chatRoomEntity) {
        entityManager.persist(chatRoomEntity);
    }

    public Optional<ChatRoomEntity> getByName(String roomName) {
        try {
            return Optional.of((ChatRoomEntity) entityManager.createQuery(
                    "SELECT c FROM ChatRoomEntity c WHERE c.roomName LIKE :name")
                    .setParameter("name", roomName)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void addMemberToRoom(String guestName, String roomByName) {
        getByName(roomByName).ifPresent(c -> {
            c.getMembers().add(guestName);
        });
    }

    public void addMessageToRoom(ChatMessageEntity chatMessageEntity) {
        var chatRoomEntityName = chatMessageEntity.getRoomName();
        var roomEntityOptional = getByName(chatRoomEntityName);
        roomEntityOptional.ifPresent(r -> {
            r.getMessages().add(chatMessageEntity);
            entityManager.persist(r);
        });
    }
}
