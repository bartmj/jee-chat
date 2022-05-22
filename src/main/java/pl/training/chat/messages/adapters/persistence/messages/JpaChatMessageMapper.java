package pl.training.chat.messages.adapters.persistence.messages;

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

    public ChatMessage toDomain(ChatMessageEntity chatMessageEntity) {
        return new ChatMessage(chatMessageEntity.getContent(), chatMessageEntity.getSenderName(), chatMessageEntity.getRoomName());
    }
}