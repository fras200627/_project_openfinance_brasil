package com.ofb.batch.service;

import com.ofb.batch.server.model.JobInformations;
import com.ofb.batch.server.model.Meta;
import com.ofb.batch.server.model.ResponseExecution;
import com.ofb.batch.server.model.ResponseJobInformations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service
public class JobLauncherService {

    @Autowired private JobLauncher jobLauncher;
    @Autowired private Job job;

    public ResponseExecution executeJobLauncher(String authorization) {

        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        return ResponseExecution.builder()
                .data(ResponseJobInformations.builder()
                        .jobInformations(JobInformations.builder()
                                .jobCreateTime(jobExecution.getCreateTime().toString())
                                .jobEndTime(jobExecution.getEndTime().toString())
                                .jobExitStatus("code: " + jobExecution.getStatus().ordinal() + " [" + jobExecution.getStatus().name() + "]")
                                .jobId(jobExecution.getId().toString())
                                .build())
                        .build())
                .meta(meta)
                .build();
    }

}
