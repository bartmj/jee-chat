package pl.training.chat.messages.domain.services;

import lombok.Setter;
import pl.training.chat.messages.adapters.persistence.files.FileUpload;
import pl.training.chat.messages.ports.ChatRoomRepository;
import pl.training.chat.messages.ports.FileRepository;
import pl.training.chat.messages.ports.FileService;

import javax.inject.Inject;

public class FileServiceImpl implements FileService {

    @Inject
    @Setter
    private FileRepository fileRepository;


    @Override
    public void save(FileUpload upload) {
        fileRepository.save(upload);
    }

    @Override
    public FileUpload get(Class<FileUpload> fileUploadClass, Long id) {
        return fileRepository.get(fileUploadClass, id);
    }
}
