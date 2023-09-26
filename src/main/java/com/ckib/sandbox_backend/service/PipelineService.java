package com.ckib.sandbox_backend.service;

import com.ckib.sandbox_backend.entity.StatsObjectInfo;
import org.gitlab4j.api.GitLabApiException;

import java.util.List;

public interface PipelineService {
    List<StatsObjectInfo> getStatistic() throws GitLabApiException;
}
