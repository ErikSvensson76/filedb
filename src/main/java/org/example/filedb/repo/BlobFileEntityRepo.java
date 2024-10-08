package org.example.filedb.repo;

import org.example.filedb.model.BlobFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlobFileEntityRepo extends JpaRepository<BlobFileEntity, String> {
  Optional<BlobFileEntity> findByFilename(String fileName);
}
