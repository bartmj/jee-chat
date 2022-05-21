package pl.training.chat.messages.domain.services;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatRoom;
import pl.training.chat.messages.domain.models.InviteRequest;
import pl.training.chat.messages.ports.ChatRoomRepository;
import pl.training.chat.messages.ports.ChatRoomService;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

public class ChatRoomServiceImpl implements ChatRoomService {

    @Inject
    @Setter
    private ChatRoomRepository chatRoomRepository;

    @Override
    public void createChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public void process(InviteRequest inviteRequest) {
        var roomByName = chatRoomRepository.getRoomByName(inviteRequest.getRoomName());
        var hostName = inviteRequest.getHostName();
        var guestName = inviteRequest.getGuestName();

        if(!roomByName.getMembers().contains(hostName) && !roomByName.getMembers().contains(guestName)) {
            throw new NotAuthorizedException("Not authorized to add users to the room.");
        } else {
            chatRoomRepository.addMemberToRoom(guestName, roomByName.getName());
        }
    }
}












