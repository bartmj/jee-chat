package pl.training.chat.messages.adapters.persistence.chatrooms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.training.chat.messages.adapters.persistence.messages.ChatMessageEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Getter
@Table(name = "rooms")
public class ChatRoomEntity {

    @Id
    @GeneratedValue
    Long id;
    String roomName;
    @ElementCollection
    List<String> members;
    @OneToMany(cascade = CascadeType.PERSIST)
    List<ChatMessageEntity> messages;

    public ChatRoomEntity(String name, List<String> members) {
        this.roomName = name;
        this.members = members;
    }

}
