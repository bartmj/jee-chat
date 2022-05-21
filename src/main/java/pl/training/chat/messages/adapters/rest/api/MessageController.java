package pl.training.chat.messages.adapters.rest.api;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Path("/messages")
public class MessageController {

    @Inject
    @Setter
    private MessageService messageService;

    @Path("send")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(ChatMessage chatMessage) throws IOException, TimeoutException {
        messageService.send(chatMessage);
        return Response.status(Response.Status.OK).build();
    }

    //testowo
    @GET
    public Response getAllMessages() {
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
