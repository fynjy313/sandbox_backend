package com.ckib.sandbox_backend.service;

import com.ckib.sandbox_backend.entity.Artifact;
import com.ckib.sandbox_backend.repository.ArtifactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtifactInfoServiceImpl implements ArtifactInfoService {
    @Autowired
    private ArtifactInfoRepository artifactInfoRepository;

    @Override
    public Artifact saveArtifact(Artifact artifact) {
        return artifactInfoRepository.save(artifact);
    }

    @Override
    public List<Artifact> getArtifacts() {
        return artifactInfoRepository.findAll();
    }

    @Override
    public Artifact updateArtifact(Artifact artifact, Long artifactId) {
        return null;
    }

    @Override
    public void deleteArtifact(Long artifactId) {
        artifactInfoRepository.deleteById(artifactId);

    }
}
