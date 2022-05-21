package pl.training.chat.messages.adapters.persistence.chatrooms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Getter
public class ChatRoomEntity {

    @Id
    @GeneratedValue
    Long id;
    String roomName;
    @ElementCollection
    List<String> members;

    public ChatRoomEntity(String name, List<String> members) {
    }
}
