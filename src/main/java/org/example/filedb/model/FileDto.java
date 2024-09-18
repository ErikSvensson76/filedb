package org.example.filedb.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FileDto implements Serializable {
  private String id;
  private String filename;
  private String mimeType;
  private String url;
}
