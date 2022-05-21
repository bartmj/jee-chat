package pl.training.chat.messages.adapters.persistence.messages;

import org.mapstruct.Mapper;
import pl.training.chat.messages.domain.models.ChatMessage;

import javax.inject.Singleton;

@Singleton
public class JpaChatMessageMapper {

    public ChatMessageEntity toEntity(ChatMessage chatMessage) {
        return new ChatMessageEntity(chatMessage.getContent(),
                chatMessage.getSenderName(),
                chatMessage.getTimestamp(),
                chatMessage.getRoomName());
    }
}
