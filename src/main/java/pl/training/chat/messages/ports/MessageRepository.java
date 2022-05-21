package pl.training.chat.messages.ports;

import pl.training.chat.messages.domain.models.ChatMessage;

public interface MessageRepository {
    void send(ChatMessage chatMessage);
}
