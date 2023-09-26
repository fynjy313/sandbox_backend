package com.ckib.sandbox_backend.service;

import com.ckib.sandbox_backend.entity.Artifact;

import java.util.List;

public interface ArtifactInfoService {
    Artifact saveArtifact(Artifact artifact);

    List<Artifact> getArtifacts();

    Artifact updateArtifact(Artifact artifact, Long artifactId);

    void deleteArtifact(Long artifactId);

}
