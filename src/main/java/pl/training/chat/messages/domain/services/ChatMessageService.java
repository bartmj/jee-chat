package pl.training.chat.messages.domain.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageRepository;
import pl.training.chat.messages.ports.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@NoArgsConstructor
public class ChatMessageService implements MessageService {

    @Inject
    @Setter
    private MessageRepository messageRepository;

    @Override
    public void send(ChatMessage chatMessage) {
        messageRepository.send(chatMessage);
    }
}
