package pl.training.chat.messages.adapters.rest.api;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatRoom;
import pl.training.chat.messages.domain.models.InviteRequest;
import pl.training.chat.messages.ports.ChatRoomService;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/rooms")
public class RoomController {

    @Inject
    @Setter
    private ChatRoomService chatRoomService;

    @POST
    public Response addNewChatRoom(ChatRoom chatRoom) {
        chatRoomService.createChatRoom(chatRoom);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    public Response inviteMemberToChatRoom(InviteRequest inviteRequest) {
        try {
            chatRoomService.process(inviteRequest);
        } catch (NotAuthorizedException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.status(Response.Status.OK).build();
    }

}
