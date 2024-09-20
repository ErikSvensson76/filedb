package org.example.filedb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BlobFileDto implements Serializable {
  private String id;
  private String filename;
  private String mimeType;
  private String url;
}
