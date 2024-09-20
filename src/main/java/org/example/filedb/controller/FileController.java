package org.example.filedb.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.filedb.model.Pair;
import org.example.filedb.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Validated
public class FileController {

  private final FileService fileService;

  @PostMapping("/files")
  public ResponseEntity<Object> uploadFile(@NotNull @RequestParam("file") MultipartFile file) {
    if(file.isEmpty()) throw new IllegalArgumentException("File is empty");

    String content = Optional.ofNullable(file.getContentType())
        .orElseThrow(() -> new IllegalArgumentException("Content type is required"));

    if(content.toLowerCase().startsWith("image")){
      return ResponseEntity.ok().body(
          fileService.postBinaryFile(file)
      );
    }else if(content.toLowerCase().startsWith("text")){
      return ResponseEntity.ok().body(
          fileService.postTextFile(file)
      );
    }else{
      throw new IllegalArgumentException("File type not supported");
    }
  }

  @GetMapping("/binary/{filename:.+}")
  public ResponseEntity<byte[]> getBinaryResource(@PathVariable("filename") String filename) {
    Pair<byte[], MediaType> pair = fileService.loadResource(filename);
    return ResponseEntity.ok().contentType(pair.getSecond()).body(pair.getFirst());
  }


}
