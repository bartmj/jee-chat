package pl.training.chat.messages.adapters.rest.api.mappers;

import pl.training.chat.messages.adapters.rest.api.dtos.ChatMessageDto;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageMapper;

public class DtoMessageMapper implements MessageMapper {

    @Override
    public ChatMessage toDomain(ChatMessageDto chatMessagedto) {
        return new ChatMessage(chatMessagedto.getContent(), chatMessagedto.getSenderName(), chatMessagedto.getRoomName());
    }
}