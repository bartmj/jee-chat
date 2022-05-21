package pl.training.chat.messages.ports;

import pl.training.chat.messages.domain.models.ChatMessage;

public interface MessageService {

    void send(ChatMessage chatMessage);

}
