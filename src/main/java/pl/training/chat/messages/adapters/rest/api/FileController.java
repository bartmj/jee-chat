package pl.training.chat.messages.adapters.rest.api;

import lombok.Setter;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.training.chat.messages.adapters.persistence.files.FileUpload;
import pl.training.chat.messages.ports.FileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Path("files")
@Stateless
public class FileController {

    @Inject
    @Setter
    private FileService fileService;

    @Path("{room}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(MultipartFormDataInput incomingFile, String roomName) throws IOException {

        InputPart inputPart = incomingFile.getFormDataMap().get("file").get(0);
        InputStream uploadedInputStream = inputPart.getBody(InputStream.class, null);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = uploadedInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        FileUpload upload = new FileUpload(
                getFileNameOfUploadedFile(inputPart.getHeaders().getFirst("Content-Disposition")),
                getContentTypeOfUploadedFile(inputPart.getHeaders().getFirst("Content-Type")),
                byteArrayOutputStream.toByteArray());

        fileService.save(upload);
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

    @Path("{room}/{id}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("room") String room ,@PathParam("id") Long id) {

        FileUpload file = fileService.get(FileUpload.class, id);

        return Response.ok(file.getData(), MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=" + file.getFileName()).build();
    }

}
