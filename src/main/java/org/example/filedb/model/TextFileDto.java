package org.example.filedb.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TextFileDto implements Serializable {
  private String id;
  private String filename;
  private String mimeType;
  private String content;
}
