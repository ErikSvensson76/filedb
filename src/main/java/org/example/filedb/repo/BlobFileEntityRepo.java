package org.example.filedb.repo;

import org.example.filedb.model.BlobFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlobFileEntityRepo extends JpaRepository<BlobFileEntity, String> {
}
