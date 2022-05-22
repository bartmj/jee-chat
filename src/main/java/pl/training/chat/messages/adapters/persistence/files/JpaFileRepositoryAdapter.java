package pl.training.chat.messages.adapters.persistence.files;

import lombok.Setter;
import pl.training.chat.messages.adapters.persistence.chatrooms.JpaChatRoomRepository;
import pl.training.chat.messages.ports.FileRepository;

import javax.inject.Inject;

public class JpaFileRepositoryAdapter implements FileRepository {

    @Inject
    @Setter
    private JpaFileRepository jpaFileRepository;

    @Override
    public void save(FileUpload upload) {
        jpaFileRepository.save(upload);
    }

    @Override
    public FileUpload get(Class<FileUpload> fileUploadClass, Long id) {
        return jpaFileRepository.get(fileUploadClass, id);
    }
}
