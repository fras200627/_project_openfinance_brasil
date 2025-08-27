package com.ofb.sbatch.consents.expiration.control.job;

import com.ofb.sbatch.consents.expiration.control.entity.ConsentPersonalData;
import com.ofb.sbatch.consents.expiration.control.repository.ConsentPersonalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.support.JdbcTransactionManager;

import javax.sql.DataSource;

@Configuration
//@EnableBatchProcessing
@Slf4j
public class ConsentExpirationControlConfig {

//    @Autowired
//    private DataSource dataSourceOracle;

//    @Bean
//    public JdbcTransactionManager transactionManager(DataSource dataSourceOracle) {
//        return new JdbcTransactionManager(dataSourceOracle);
//    }

    @Bean
    public JdbcCursorItemReader<ConsentPersonalData> itemReader() {
        String sql = "select * from ConsentPersonalData";
        return new JdbcCursorItemReaderBuilder<ConsentPersonalData>()
                .name("personItemReader")
                .dataSource(dataSourceOracle)
                .sql(sql)
                .beanRowMapper(ConsentPersonalData.class)
                .build();
    }

    @Bean
    public FlatFileItemWriter<ConsentPersonalData> itemWriter() {
        return new FlatFileItemWriterBuilder<ConsentPersonalData>()
                .resource(new FileSystemResource("consents.csv"))
                .name("personItemWriter")
                .delimited()
                .names("consentId", "status")
                .build();
    }

    @Bean
    public Job job() {
        return new JobBuilder("job")
                .start(new StepBuilder("step")
                        .<ConsentPersonalData, ConsentPersonalData>chunk(1)
                        .reader(itemReader())
                        .writer(itemWriter())
                        .build())
                .build();
    }

}
