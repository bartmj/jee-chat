package pl.training.chat.messages.adapters.persistence.files;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaFileRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(FileUpload upload) {
        entityManager.persist(upload);
    }

    public FileUpload get(Class<FileUpload> fileUploadClass, Long id) {
        return entityManager.find(fileUploadClass, id);
    }
}
