package pl.training.chat.messages.adapters.persistence.chatrooms;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaChatRoomRepository {

    @Setter
    @PersistenceContext
    EntityManager entityManager;

    public void save(ChatRoomEntity chatMessage) {
        entityManager.persist(chatMessage);
    }

    public ChatRoomEntity getByName(String roomName) {
        return (ChatRoomEntity) entityManager.createQuery(
                "SELECT c FROM ChatRoomEntity c WHERE c.roomName LIKE :name")
                .setParameter("name", roomName)
                .getSingleResult();
    }

    public void addMemberToRoom(String guestName, String roomByName) {
        var chatRoomEntity = getByName(roomByName);
        chatRoomEntity.getMembers().add(guestName);
    }
}
