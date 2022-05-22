package pl.training.chat.messages.domain.services;

import lombok.Setter;
import pl.training.chat.messages.domain.exceptions.RoomAlreadyExistsException;
import pl.training.chat.messages.domain.models.ChatRoom;
import pl.training.chat.messages.domain.models.InviteRequest;
import pl.training.chat.messages.ports.ChatRoomRepository;
import pl.training.chat.messages.ports.ChatRoomService;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import java.util.Optional;

public class ChatRoomServiceImpl implements ChatRoomService {

    @Inject
    @Setter
    private ChatRoomRepository chatRoomRepository;

    @Override
    public void createChatRoom(ChatRoom chatRoom) {
        var roomByName = chatRoomRepository.getRoomByName(chatRoom.getName());
        if (roomByName.isEmpty()) {
            chatRoomRepository.save(chatRoom);
        } else {
            throw new RoomAlreadyExistsException();
        }
    }

    @Override
    public void process(InviteRequest inviteRequest) {
        var roomByName = chatRoomRepository.getRoomByName(inviteRequest.getRoomName());
        var hostName = inviteRequest.getHostName();
        var guestName = inviteRequest.getGuestName();

        var memberAuthorizedToJoin = roomByName.map(r -> r.getMembers().contains(hostName) && r.getMembers().contains(guestName))
                .isPresent();

        if(memberAuthorizedToJoin) {
            chatRoomRepository.addMemberToRoom(guestName, inviteRequest.getRoomName());
        } else {
            throw new NotAuthorizedException("Not authorized to add users to the room.");
        }

    }
}












