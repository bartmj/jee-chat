package pl.training.chat.messages.ports;

import pl.training.chat.messages.domain.models.ChatRoom;
import pl.training.chat.messages.domain.models.InviteRequest;

public interface ChatRoomService {
    void createChatRoom(ChatRoom chatRoom);

    void process(InviteRequest inviteRequest);
}
