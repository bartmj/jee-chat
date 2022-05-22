package pl.training.chat.messages.adapters.rest.api.dtos;

import lombok.Data;

@Data
public class ChatMessageDto {

    String content;
    String senderName;
    String roomName;

}
