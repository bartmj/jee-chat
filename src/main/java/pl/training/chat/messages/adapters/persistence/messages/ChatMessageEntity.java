package pl.training.chat.messages.adapters.persistence.messages;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Getter
public class ChatMessageEntity {

    @Id
    @GeneratedValue
    Long id;
    String content;
    String senderName;
    Instant timestamp;
    String roomName;

    public ChatMessageEntity(String content, String senderName, Instant timestamp, String roomName) {
    }
}
