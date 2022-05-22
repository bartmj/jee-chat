package pl.training.chat.messages.adapters.persistence.chatrooms;

import lombok.Setter;
import pl.training.chat.messages.adapters.persistence.messages.ChatMessageEntity;
import pl.training.chat.messages.adapters.persistence.messages.JpaChatMessageMapper;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.domain.models.ChatRoom;
import pl.training.chat.messages.ports.ChatRoomRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class JpaChatRoomRepositoryAdapter implements ChatRoomRepository {

    @Inject
    @Setter
    private JpaChatRoomRepository jpaChatRoomRepository;
    @Inject
    @Setter
    private JpaChatRoomMapper jpaChatRoomMapper;
    @Inject
    @Setter
    private JpaChatMessageMapper jpaChatMessageMapper;

    @Override
    public void save(ChatRoom chatRoom) {
        var chatMessageEntity = jpaChatRoomMapper.toEntity(chatRoom);
        jpaChatRoomRepository.save(chatMessageEntity);
    }

    @Override
    public Optional<ChatRoom> getRoomByName(String roomName) {
        var roomEntity = jpaChatRoomRepository.getByName(roomName);
        return roomEntity.map(r -> jpaChatRoomMapper.toDomain(r));
    }

    @Override
    public void addMemberToRoom(String guestName, String roomName) {
        jpaChatRoomRepository.addMemberToRoom(guestName, roomName);
    }

    @Override
    public List<ChatMessage> getMemberHistory(String memberName, String roomName) {
        var chatMessageEntitiesOptionalList = jpaChatRoomRepository.getByName(roomName).map(ChatRoomEntity::getMessages);
        var chatMessageEntitiesExist = chatMessageEntitiesOptionalList.isPresent();
        if (chatMessageEntitiesExist) {
            return chatMessageEntitiesOptionalList.get().stream()
                    .filter(chatMessageEntity -> chatMessageEntity.getSenderName().equals(memberName))
                    .map(chatMessageEntity -> jpaChatMessageMapper.toDomain(chatMessageEntity))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void addMessageToRoom(ChatMessage chatMessage) {
        var chatMessageEntity = jpaChatMessageMapper.toEntity(chatMessage);
        jpaChatRoomRepository.addMessageToRoom(chatMessageEntity);
    }

}
