package pl.training.chat.messages.adapters.persistence.files;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FileUpload {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  private String fileName;
 
  private String contentType;
 
  @Lob
  private byte[] data;

  public FileUpload(String fileName, String contentType, byte[] data) {
    this.fileName = fileName;
    this.contentType = contentType;
    this.data = data;
  }

}