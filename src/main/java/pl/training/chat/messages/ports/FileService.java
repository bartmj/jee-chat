package pl.training.chat.messages.ports;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.training.chat.messages.adapters.persistence.files.FileUpload;

import java.io.IOException;

public interface FileService {

    void save(FileUpload upload);

    FileUpload get(Class<FileUpload> fileUploadClass, Long id);

    FileUpload getFileUpload(MultipartFormDataInput incomingFile) throws IOException;
}
