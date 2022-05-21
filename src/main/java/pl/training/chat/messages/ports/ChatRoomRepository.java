package pl.training.chat.messages.ports;

import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.domain.models.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository {
    void save(ChatRoom chatRoom);

    Optional<ChatRoom> getRoomByName(String roomName);

    void addMemberToRoom(String guestName, String roomName);

    void getMemberHistory(String memberName, String roomName);

    void addMessageToRoom(ChatMessage chatMessage);
}
