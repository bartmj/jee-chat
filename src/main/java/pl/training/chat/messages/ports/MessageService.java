package pl.training.chat.messages.ports;

import pl.training.chat.messages.domain.models.ChatMessage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface MessageService {

    void send(ChatMessage chatMessage) throws IOException, TimeoutException;

    List<ChatMessage> getRoomHistoryOfMember(String memberName, String roomName);

}

