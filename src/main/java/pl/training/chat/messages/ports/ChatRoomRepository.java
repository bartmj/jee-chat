package pl.training.chat.messages.ports;

import pl.training.chat.messages.domain.models.ChatRoom;

public interface ChatRoomRepository {
    void save(ChatRoom chatRoom);

    ChatRoom getRoomByName(String roomName);

    void addMemberToRoom(String guestName, String roomName);

}
