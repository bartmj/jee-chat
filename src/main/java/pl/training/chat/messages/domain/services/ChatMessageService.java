package pl.training.chat.messages.domain.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageRepository;
import pl.training.chat.messages.ports.MessageService;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
@NoArgsConstructor
public class ChatMessageService implements MessageService {

    private MessageRepository messageRepository;

    @Override
    public void send(ChatMessage chatMessage) {
        messageRepository.send(chatMessage);
    }
}
