package pl.training.chat.messages.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Builder
@Value
@Getter
public class ChatMessage {

    String id;
    String content;
    String senderName;
    Instant timestamp;
    String RoomName;
    List<String> members;

}
