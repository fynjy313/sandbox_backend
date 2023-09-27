package com.ckib.sandbox_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsObjectInfo {
    private long pipelineId;
    private long jobId;
    private String createdAt;
    private String testImageName;// key=TEST_IMAGE
    private String jobStatus;

    @Override
    public String toString() {
        return "StatsObjectInfo{" +
                "pipelineId=" + pipelineId +
                ", jobId=" + jobId +
                ", createdAt='" + createdAt + '\'' +
                ", testImageName='" + testImageName + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                '}';
    }
}
