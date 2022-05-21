package pl.training.chat.messages.adapters.rest.api;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("messages")
@Setter
public class MessageController {

    @Inject
    private MessageService messageService;

    @POST
    public Response sendMessage(ChatMessage chatMessage) {
        messageService.send(chatMessage);
        return Response.status(Response.Status.OK).build();
    }

//    @Path("history/{roomName}")
//    @GET
//    public Response getMessageHistory(@PathParam("roomName") String channelName,
//                                      @QueryParam("memberName") String memberName) {
//        messageService.
//        return Response.status(Response.Status.OK).build();
//    }

}
