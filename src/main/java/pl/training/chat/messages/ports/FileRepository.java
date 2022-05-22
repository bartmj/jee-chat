package pl.training.chat.messages.ports;

import pl.training.chat.messages.adapters.persistence.files.FileUpload;

public interface FileRepository {
    void save(FileUpload upload);

    FileUpload get(Class<FileUpload> fileUploadClass, Long id);
}
