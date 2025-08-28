package com.ofb.sbatch.consents.approval.control.config.job;

import com.ofb.sbatch.consents.approval.control.entity.ConsentPersonalData;
import com.ofb.sbatch.consents.approval.control.repository.ConsentPersonalRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import java.util.*;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
public class ConsentApprovalControlJob {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.audit.consents-revoke.routing-key}")
    private String AUDIT_CONSENTS_REVOKE_ROUTING_KEY;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean(name = "consentJob")
    public Job job(@Qualifier("approvalVerifyStep") Step expirationControlStart) {
        return jobBuilderFactory.get("consentJob")
                .incrementer(new RunIdIncrementer())
                .start(expirationControlStart)
                .build();
    }

    @Bean
    protected Step approvalVerifyStep(ItemReader reader, ItemWriter writer) throws ParseException {
        return stepBuilderFactory
                .get("approvalVerifyStep")
                .transactionManager(platformTransactionManager)
                .<ConsentPersonalData, ConsentPersonalData>chunk(1)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public RepositoryItemReader<ConsentPersonalData> reader(ConsentPersonalRepository repository) {
        //String statusToRead = "AUTHORISED";
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("consentId", Sort.Direction.ASC);

        return new RepositoryItemReaderBuilder<ConsentPersonalData>()
                .repository(repository)
                .methodName("findAllConsentsApprovalExpired")
                //.arguments(Collections.singletonList(statusToRead)) // Pass the argument
                .sorts(sorts)
                .pageSize(10)
                .name("allConsents")
                .build();
    }

    @Bean
    public RepositoryItemWriter<ConsentPersonalData> writer(ConsentPersonalRepository repository) {
        final RepositoryItemWriter<ConsentPersonalData> repositoryItemWriter = new RepositoryItemWriter<>();
        repositoryItemWriter.setRepository(repository);
        repositoryItemWriter.setMethodName("save");
        return repositoryItemWriter;
    }

    @Bean
    public ConsentApprovalProcessor processor() {
        return new ConsentApprovalProcessor();
    }

}
