package pl.training.chat.messages.domain.models;

import lombok.*;

import java.time.Instant;

@Data
public class ChatMessage {

    String content;
    String senderName;
    Instant timestamp;
    String roomName;

    public String toJsonString() {
        return String.format("{\"content\":\"%s\",\"senderName\":\"%s\",\"timestamp\":\"%s\",\"roomName\":\"%s\"}", content, senderName, timestamp, roomName);
    }

}
