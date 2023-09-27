package com.ckib.sandbox_backend.service;

import com.ckib.sandbox_backend.entity.StatsObjectInfo;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.JobApi;
import org.gitlab4j.api.PipelineApi;
import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PipelineServiceImpl implements PipelineService {
    private final static String GITLAB_URL = "https://gitlab.ssdlc.online";
    private final static String GITLAB_ACCESS_TOKEN = "glpat-Xtx2KPyjoMCEk2TWbpv6"; //ias: "Kosmoport/Container_scan_pipeline" OWNER
    private final static String PROJECT_PATH = "kosmoport/container_scan_task";

    @Override
    public List<StatsObjectInfo> getStatistic() throws GitLabApiException {
        GitLabApi gitLabApi = new GitLabApi(GITLAB_URL, GITLAB_ACCESS_TOKEN);
        PipelineApi pipelineApi = gitLabApi.getPipelineApi();
        JobApi jobApi = gitLabApi.getJobApi();
        List<Variable> variableList;
        long pipelineId;
        long jobId;
        String createdAt;
        String testImageName = null;
        String jobStatus;
        List<StatsObjectInfo> statsObjectInfoList = new ArrayList<>();
/*        jobApi.getJobsStream(PROJECT_PATH).forEach(job -> {
            //variableList = pipelineApi.getPipelineVariables("kosmoport/container_scan_task", pipelineId);
        });*/
        for (Job job : jobApi.getJobs(PROJECT_PATH)) {
            pipelineId = job.getPipeline().getId();
            jobId = job.getId();
            createdAt = String.valueOf(job.getCreatedAt());
            variableList = pipelineApi.getPipelineVariables(PROJECT_PATH, pipelineId);
            try {
                testImageName = variableList.stream().filter(variable -> variable.getKey().equals("TEST_IMAGE")).findFirst().get().getValue();
                //variableList.stream().filter(variable -> variable.getKey().equals("TEST_IMAGE")).collect(Collectors.toList()).get(0).getValue();
            } catch (NoSuchElementException ignored) {
            }
            jobStatus = String.valueOf(job.getStatus());
            statsObjectInfoList.add(new StatsObjectInfo(pipelineId, jobId, createdAt, testImageName, jobStatus));
        }
        System.out.println(statsObjectInfoList);

//        job.pipeLineId, job.jobId, job.createdAt, pipelineVariables.testImageName, job.status
//        System.out.println("\n***************************************\n");
//        System.out.println(statsObjectInfoList);

        return statsObjectInfoList;
    }
}
