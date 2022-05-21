package pl.training.chat.messages.adapters.persistence.chatrooms;

import lombok.Setter;
import pl.training.chat.messages.domain.models.ChatRoom;
import pl.training.chat.messages.ports.ChatRoomRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
public class JpaChatRoomRepositoryAdapter implements ChatRoomRepository {

    @Inject
    @Setter
    private JpaChatRoomRepository jpaChatRoomRepository;
    @Inject
    @Setter
    private JpaChatRoomMapper jpaChatRoomMapper;

    @Override
    public void save(ChatRoom chatRoom) {
        var chatMessageEntity = jpaChatRoomMapper.toEntity(chatRoom);
        jpaChatRoomRepository.save(chatMessageEntity);
    }

    @Override
    public ChatRoom getRoomByName(String roomName) {
        var byName = jpaChatRoomRepository.getByName(roomName);
        return jpaChatRoomMapper.toDomain(byName);
    }

    @Override
    public void addMemberToRoom(String guestName, String roomName) {
        jpaChatRoomRepository.addMemberToRoom(guestName, roomName);
    }

}
