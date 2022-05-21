package pl.training.chat.messages.domain.models;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;


@Data
public class ChatMessage {

    String content;
    String senderName;
    Instant timestamp;
    String roomName;
//    List<String> members;

}
