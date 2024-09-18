package org.example.filedb.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blob_file")
public class BlobFileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "pk_blob_file", updatable = false)
  private String id;
  @Column(name = "filename", unique = true)
  private String filename;
  @Column(name = "mime_type")
  private String mimeType;
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "blob")
  private Byte[] file;

}
