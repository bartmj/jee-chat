package pl.training.chat.messages.domain.services;

import lombok.Setter;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.training.chat.messages.adapters.persistence.files.FileUpload;
import pl.training.chat.messages.ports.FileRepository;
import pl.training.chat.messages.ports.FileService;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @Override
    public FileUpload getFileUpload(MultipartFormDataInput incomingFile) throws IOException {
        InputPart inputPart = incomingFile.getFormDataMap().get("file").get(0);
        InputStream uploadedInputStream = inputPart.getBody(InputStream.class, null);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = uploadedInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        return new FileUpload(
                getFileNameOfUploadedFile(inputPart.getHeaders().getFirst("Content-Disposition")),
                getContentTypeOfUploadedFile(inputPart.getHeaders().getFirst("Content-Type")),
                byteArrayOutputStream.toByteArray());
    }

    private String getContentTypeOfUploadedFile(String contentTypeHeader) {
        if (contentTypeHeader == null || contentTypeHeader.isEmpty()) {
            return "unknown";
        } else {
            return contentTypeHeader.replace("[", "").replace("]", "");
        }
    }

    private String getFileNameOfUploadedFile(String contentDispositionHeader) {

        if (contentDispositionHeader != null && !contentDispositionHeader.isEmpty()) {
            String[] contentDispositionHeaderTokens = contentDispositionHeader.split(";");

            for (String contentDispositionHeaderToken : contentDispositionHeaderTokens) {
                if ((contentDispositionHeaderToken.trim().startsWith("filename"))) {
                    return contentDispositionHeaderToken.split("=")[1].trim().replace("\"", "");
                }
            }
        }
        return "unknown";
    }
}
