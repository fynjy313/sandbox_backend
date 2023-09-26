package com.ckib.sandbox_backend.controller;

import com.ckib.sandbox_backend.entity.Artifact;
import com.ckib.sandbox_backend.service.ArtifactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;

import java.util.List;

@RestController
public class ArtifactInfoController {

    @Autowired
    private ArtifactInfoService artifactInfoService;

    // Create operation
    @PostMapping("/artifact")
    public Artifact saveArtifact(/*@Valid*/ @RequestBody Artifact artifact) {
        return artifactInfoService.saveArtifact(artifact);
    }

    // Read operation
    @GetMapping("/artifacts")
    public List<Artifact> getArtifacts() {
        return artifactInfoService.getArtifacts();
    }

    // Update operation
    @PutMapping("/artifacts/{id}")
    public Artifact updateArtifact(@RequestBody Artifact artifact, @PathVariable("id") Long artifactId) {
        return artifactInfoService.updateArtifact(
                artifact, artifactId);
    }

    // Delete operation
    @DeleteMapping("/artifacts/{id}")
    public String deleteArtifactById(@PathVariable("id") Long artifactId) {
        artifactInfoService.deleteArtifact(artifactId);
        return "Deleted Successfully";
    }

}
