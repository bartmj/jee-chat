package pl.training.chat.messages.domain.models;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@EqualsAndHashCode
public class ChatMessage implements Serializable {

    String content;
    String senderName;
    Instant timestamp;
    String roomName;

    public ChatMessage(String content, String senderName, String roomName) {
        this.content = content;
        this.senderName = senderName;
        this.roomName = roomName;
    }

    public ChatMessage() {
    }

    public String toJsonString() {
        return String.format("{\"content\":\"%s\",\"senderName\":\"%s\",\"timestamp\":\"%s\",\"roomName\":\"%s\"}", content, senderName, timestamp, roomName);
    }

}
