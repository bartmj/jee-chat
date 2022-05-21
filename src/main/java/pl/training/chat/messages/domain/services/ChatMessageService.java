package pl.training.chat.messages.domain.services;

import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.training.chat.messages.adapters.JmsMessageService;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageRepository;
import pl.training.chat.messages.ports.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Singleton
@NoArgsConstructor
public class ChatMessageService implements MessageService {

    @Inject
    @Setter
    private MessageRepository messageRepository;

    @Inject
    @Setter
    JmsMessageService jmsMessageService;

    @Override
    public void send(ChatMessage chatMessage) throws IOException, TimeoutException {
        messageRepository.send(chatMessage);
        jmsMessageService.send(chatMessage);
    }
}
