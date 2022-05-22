package pl.training.chat.messages.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatRoom {
    String name;
    List<String> members;

    public ChatRoom(String roomName, List<String> members) {
    }
}
