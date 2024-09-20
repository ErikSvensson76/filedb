package org.example.filedb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class Pair <T1, T2 >{
  private T1 first;
  private T2 second;
}
