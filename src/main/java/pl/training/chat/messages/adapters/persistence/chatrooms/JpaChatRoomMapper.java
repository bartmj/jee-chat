package pl.training.chat.messages.adapters.persistence.chatrooms;


import pl.training.chat.messages.domain.models.ChatRoom;

public class JpaChatRoomMapper {

    ChatRoomEntity toEntity(ChatRoom chatRoom) {
        return new ChatRoomEntity(chatRoom.getName(),
                chatRoom.getMembers());
    }

    public ChatRoom toDomain(ChatRoomEntity chatRoomEntity) {
        return new ChatRoom(chatRoomEntity.getRoomName(),
                chatRoomEntity.getMembers());
    }
}
