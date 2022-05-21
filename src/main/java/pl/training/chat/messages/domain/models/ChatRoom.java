package pl.training.chat.messages.domain.models;

import lombok.Data;

import java.util.List;

@Data
public class ChatRoom {
    String name;
    List<String> members;

    public ChatRoom(String roomName, List<String> members) {
    }
}
