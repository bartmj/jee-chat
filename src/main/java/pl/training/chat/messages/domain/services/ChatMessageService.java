package pl.training.chat.messages.domain.services;

import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.training.chat.messages.adapters.JmsMessageService;
import pl.training.chat.messages.domain.exceptions.RoomNotFoundException;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.ChatRoomRepository;
import pl.training.chat.messages.ports.MessageRepository;
import pl.training.chat.messages.ports.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
@NoArgsConstructor
public class ChatMessageService implements MessageService {

    @Inject
    @Setter
    private ChatRoomRepository chatRoomRepository;

    @Inject
    @Setter
    private MessageRepository messageRepository;

    @Inject
    @Setter
    JmsMessageService jmsMessageService;

    @Override
    public void send(ChatMessage chatMessage) throws IOException, TimeoutException {
        messageRepository.send(chatMessage);
        var roomByName = chatRoomRepository.getRoomByName(chatMessage.getRoomName());
        var roomPresent = roomByName.isPresent();
        if (roomPresent) {
            chatRoomRepository.addMessageToRoom(chatMessage);
            jmsMessageService.send(chatMessage);
        } else {
            throw new RoomNotFoundException();
        }
    }

    @Override
    public List<ChatMessage> getRoomHistoryOfMember(String memberName, String roomName) {
        var memberHistory = chatRoomRepository.getMemberHistory(memberName, roomName);
        return memberHistory;
    }
}












