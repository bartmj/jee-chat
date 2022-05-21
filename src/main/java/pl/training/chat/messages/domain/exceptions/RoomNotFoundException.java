package pl.training.chat.messages.domain.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("Room does not exit!");
    }
}
