package pl.training.chat.messages.adapters.rest.api;

import lombok.Setter;
import pl.training.chat.messages.adapters.rest.api.dtos.ChatMessageDto;
import pl.training.chat.messages.adapters.rest.api.mappers.DtoMessageMapper;
import pl.training.chat.messages.domain.exceptions.RoomNotFoundException;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Path("/messages")
public class MessageController {

    @Inject
    @Setter
    private MessageService messageService;
    @Inject
    @Setter
    private DtoMessageMapper messageMapper;

    @Path("send")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(ChatMessageDto chatMessagedto) throws IOException, TimeoutException {
        var chatMessage = messageMapper.toDomain(chatMessagedto);
        try {
            messageService.send(chatMessage);
        } catch (RoomNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }


    @Path("history/{roomName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageHistory(@PathParam("roomName") String roomName,
                                      @QueryParam("memberName") String memberName) {
        var roomHistoryOfMember = messageService.getRoomHistoryOfMember(memberName, roomName);
        return Response.status(Response.Status.OK)
                .entity(roomHistoryOfMember)
                .build();
    }

}
