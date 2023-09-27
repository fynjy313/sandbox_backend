package com.ckib.sandbox_backend;

import com.ckib.sandbox_backend.entity.StatsObjectInfo;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.JobApi;
import org.gitlab4j.api.PipelineApi;
import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Variable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TestGitlabApi {
    private final static String GITLAB_URL = "https://gitlab.ssdlc.online";
    private final static String GITLAB_ACCESS_TOKEN_ban = "glptt-74cddf342f00b35b735f8371b6c586fc7c23de9e"; //my
    private final static String GITLAB_ACCESS_TOKEN = "glpat-Xtx2KPyjoMCEk2TWbpv6"; //ias: "Kosmoport/Container_scan_pipeline" OWNER
    private final static String PROJECT_PATH = "kosmoport/container_scan_task";

    ///api/v4/projects/8/ref/send-report-to-nexus/trigger/pipeline?token=glptt-74cddf342f00b35b735f8371b6c586fc7c23de9e&variables[TEST_IMAGE]=harbor.stage.local/ssdlc/yandex-cloud-devsecops@sha256:2e78f63ae7b0be9e5d0dbe409bc6e647978d68fad5d5e601b5764167f96db47d
    @Test
    public void workApiGetInfoAboutPipeline() throws GitLabApiException {
        GitLabApi gitLabApi = new GitLabApi(GITLAB_URL, GITLAB_ACCESS_TOKEN);

        PipelineApi pipelineApi = gitLabApi.getPipelineApi();


        System.out.println(pipelineApi.getPipelines("kosmoport/container_scan_task"));
//        System.out.println(gitLabApi.getProjectApi().getProjects().stream()
//                .filter(project -> project.getPath().contains("container_scan_task")).collect(Collectors.toList()));
        System.out.println("\n\n*******************************\n\n");

        Long pipelineId = 317L;
        //curl --location --request GET 'https://gitlab.ssdlc.online/api/v4/projects/8/pipelines/317/variables' \
        //--header 'PRIVATE-TOKEN: qwerty123'
        List<Variable> variableList = pipelineApi.getPipelineVariables("kosmoport/container_scan_task", pipelineId);
        System.out.println("\n***************************************\n");

        //System.out.println(variableList.stream().filter(variable -> variable.getKey().equals("TEST_IMAGE")).collect(Collectors.toList()).get(0).getValue());
        System.out.println(variableList.stream().filter(variable -> variable.getKey().equals("TEST_IMAGE")).findFirst().get().getValue());
    }


    @Test
    public void testApi() throws GitLabApiException {
        System.out.println(System.getProperty("os.name"));

        // Create a GitLabApi instance to communicate with your GitLab server
        GitLabApi gitLabApi = new GitLabApi(GITLAB_URL, GITLAB_ACCESS_TOKEN);

// Get the list of projects your account has access to
        List<Project> projects = gitLabApi.getProjectApi().getProjects();
        projects.forEach(System.out::println);

/*        // Get a Pager instance to that will be used to lazily stream Project instances.
        // In this example, 10 Projects per page will be pre-fetched.
        Pager<Project> projectPager = gitLabApi.getProjectApi().getProjects(10);

        // Lazily stream the Projects, printing out each project name, limit the output to 5 project names
        projectPager.lazyStream().limit(5).map(Project::getName).forEach(System.out::println);*/
    }

    @Test
    public void testPipeLineServiceWithJobApi() throws GitLabApiException {
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

//job.pipeLineId, job.jobId, job.createdAt, pipelineVariables.testImageName, job.status
    }
}
