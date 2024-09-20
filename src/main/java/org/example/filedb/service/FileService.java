package org.example.filedb.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.filedb.config.BaseUrlConfig;
import org.example.filedb.exception.AppResourceNotFoundException;
import org.example.filedb.model.*;
import org.example.filedb.repo.BlobFileEntityRepo;
import org.example.filedb.repo.ClobFileEntityRepo;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Validated
public class FileService {

  private final ClobFileEntityRepo clobFileEntityRepo;
  private final BlobFileEntityRepo blobFileEntityRepo;
  private final BaseUrlConfig baseUrlConfig;

  public TextFileDto postTextFile(@NotNull MultipartFile file) {

    Resource resource = file.getResource();
    String content = null;
    try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      content = reader.lines()
          .collect(Collectors.joining("\n"));
    }catch (IOException ioException){
      log.error("Could not read from file", ioException);
    }
    if(content == null){
      throw new IllegalArgumentException("Could not read file");
    }

    var entity = clobFileEntityRepo.save(
        ClobFileEntity.builder()
        .filename(file.getOriginalFilename())
        .mimeType(file.getContentType())
        .file(content)
        .build()
    );

    TextFileDto dto = new TextFileDto();
    dto.setId(entity.getId());
    dto.setFilename(entity.getFilename());
    dto.setMimeType(entity.getMimeType());
    dto.setContent(entity.getFile());

    return dto;
  }

  public BlobFileDto postBinaryFile(@NotNull MultipartFile file) {
    byte[] bytes = null;
    Resource resource = file.getResource();
    try (BufferedInputStream inputStream = new BufferedInputStream(resource.getInputStream())) {
      bytes = inputStream.readAllBytes();
    } catch (IOException ioException) {
      log.error("Could not read from file", ioException);
    }
    if (bytes == null) {
      throw new IllegalArgumentException("Could not read file");
    }

    var entity = blobFileEntityRepo.save(BlobFileEntity.builder()
          .filename(file.getOriginalFilename())
          .mimeType(file.getContentType())
          .file(bytes)
          .build()
    );

    BlobFileDto dto = new BlobFileDto();
    dto.setId(entity.getId());
    dto.setFilename(entity.getFilename());
    dto.setMimeType(entity.getMimeType());
    dto.setUrl(baseUrlConfig.getUrl() + "/binary/" + entity.getFilename());
    return dto;
  }

  public Pair<byte[], MediaType> loadResource(@NotNull String filename) {
    return blobFileEntityRepo.findByFilename(filename)
        .map(entity -> new Pair<>(entity.getFile(), MediaType.parseMediaType(entity.getMimeType())))
        .orElseThrow(AppResourceNotFoundException.getInstanceSupplier(String.format(
            "Could not find file %s", filename
        )));

  }

}
