package org.example.filedb.repo;

import org.example.filedb.model.ClobFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClobFileEntityRepo extends JpaRepository<ClobFileEntity, String> {
}
