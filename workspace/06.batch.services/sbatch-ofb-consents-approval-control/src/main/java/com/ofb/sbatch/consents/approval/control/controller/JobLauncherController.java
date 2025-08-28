package com.ofb.sbatch.consents.approval.control.controller;

import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class JobLauncherController {

    @Autowired private JobLauncher jobLauncher;

    @Autowired private Job job;

    @GetMapping("/batch/approval/jobLauncher")
    @CanSystemOFBAdmin
    public String handle() throws Exception{
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
        return jobExecution.getCreateTime().toString() + " / " +
        jobExecution.getEndTime().toString() + " / " +
        jobExecution.getExitStatus().toString();

    }
}