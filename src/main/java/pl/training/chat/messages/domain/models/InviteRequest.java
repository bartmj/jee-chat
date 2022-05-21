package pl.training.chat.messages.domain.models;

import lombok.Data;

@Data
public class InviteRequest {
    String hostName;
    String guestName;
    String roomName;
}
