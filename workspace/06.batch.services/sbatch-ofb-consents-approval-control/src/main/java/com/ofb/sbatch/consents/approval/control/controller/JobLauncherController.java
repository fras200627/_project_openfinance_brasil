package com.ofb.sbatch.consents.approval.control.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping("/jobLauncher")
    public String handle() throws Exception{
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
        return jobExecution.getCreateTime().toString() + " / " +
        jobExecution.getEndTime().toString() + " / " +
        jobExecution.getExitStatus().toString();

    }
}