package com.ckib.sandbox_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsObjectInfo {
    //inf from pipelineApi.getPipelines("kosmoport/container_scan_task")
    private String pipelineId; //'id' 317
    private String status;
    private String createdAt;

    //inf from pipelineApi.getPipelineVariables("kosmoport/container_scan_task", pipelineId)
    private String testImageName; // key=TEST_IMAGE

    @Override
    public String toString() {
        return "StatsObjectInfo{" +
                "pipelineId='" + pipelineId + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", testImageName='" + testImageName + '\'' +
                '}';
    }
}
