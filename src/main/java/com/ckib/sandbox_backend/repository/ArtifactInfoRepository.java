package com.ckib.sandbox_backend.repository;

import com.ckib.sandbox_backend.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactInfoRepository extends JpaRepository<Artifact, Long> { //extends CrudRepository
}
