package com.ckib.sandbox_backend.service;

import com.ckib.sandbox_backend.entity.StatsObjectInfo;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.PipelineApi;
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
    private static final String PROJECT_ID_OR_PATH = "kosmoport/container_scan_task";

    @Override
    public List<StatsObjectInfo> getStatistic() throws GitLabApiException {
/*        List<JobInfo> jobInfos = new ArrayList<>();
        jobInfos.add(new JobInfo("2023-09-22", "artifact 1", "Done"));
        jobInfos.add(new JobInfo("2023-09-23", "artifact 2", "Fail"));
        jobInfos.add(new JobInfo("2023-09-24", "artifact 3", "In process"));
        return jobInfos;*/
//        return new ArrayList<>(Arrays.asList("Job1", "Job2", "Job3"));

        List<StatsObjectInfo> statsObjectInfoList = new ArrayList<>();
        //String pipelineId;
        long pipelineId;
        String status;
        String createdAt;
        String testImageName = null;
        List<Variable> variableList;
        PipelineApi pipelineApi = new GitLabApi(GITLAB_URL, GITLAB_ACCESS_TOKEN).getPipelineApi();
        List<Pipeline> pipelineList = pipelineApi.getPipelines(PROJECT_ID_OR_PATH);

        for (Pipeline pipeline : pipelineList) {
            pipelineId = pipeline.getId();
            status = String.valueOf(pipeline.getStatus());
            createdAt = String.valueOf(pipeline.getCreatedAt());
            variableList = pipelineApi.getPipelineVariables(PROJECT_ID_OR_PATH, pipelineId);
            try {
                testImageName = variableList.stream().filter(variable -> variable.getKey().equals("TEST_IMAGE")).findFirst().get().getValue();
            } catch (NoSuchElementException ignored) {
            }
            //variableList.stream().filter(variable -> variable.getKey().equals("TEST_IMAGE")).collect(Collectors.toList()).get(0).getValue();
            statsObjectInfoList.add(new StatsObjectInfo(pipelineId + "", status, createdAt, testImageName));
        }
//        System.out.println("\n***************************************\n");
//        System.out.println(statsObjectInfoList);

        return statsObjectInfoList;
    }
}
