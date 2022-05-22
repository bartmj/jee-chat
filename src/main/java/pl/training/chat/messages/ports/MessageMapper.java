package pl.training.chat.messages.ports;

import pl.training.chat.messages.adapters.rest.api.dtos.ChatMessageDto;
import pl.training.chat.messages.domain.models.ChatMessage;

public interface MessageMapper {
    ChatMessage toDomain(ChatMessageDto chatMessagedto);
}
