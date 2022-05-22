package pl.training.chat.messages.domain.exceptions;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException() { super("Room with this name already exitst.");
    }
}
