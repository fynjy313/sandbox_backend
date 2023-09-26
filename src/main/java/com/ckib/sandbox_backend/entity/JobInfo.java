package com.ckib.sandbox_backend.entity;

public class JobInfo {
    private String date;
    private String artifactName;
    private String jobStatus;

    public JobInfo(String date, String artifactName, String jobStatus) {
        this.date = date;
        this.artifactName = artifactName;
        this.jobStatus = jobStatus;
    }

    public String getDate() {
        return date;
    }

    public String getArtifactName() {
        return artifactName;
    }

    public String getJobStatus() {
        return jobStatus;
    }
}
