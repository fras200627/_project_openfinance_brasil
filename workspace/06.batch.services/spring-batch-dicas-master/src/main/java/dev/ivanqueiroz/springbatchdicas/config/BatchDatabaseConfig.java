package dev.ivanqueiroz.springbatchdicas.config;

import com.zaxxer.hikari.HikariDataSource;
import dev.ivanqueiroz.springbatchdicas.batch.JobCompletionNotificationListener;
import dev.ivanqueiroz.springbatchdicas.batch.Pessoa;
import dev.ivanqueiroz.springbatchdicas.batch.PessoaItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchDatabaseConfig {

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.configuration")
  public HikariDataSource dataSource(DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
  /**
   * Datasource para o repositório do batch
   */
  @Bean
  @BatchDataSource
  DataSource batchDataSource() {
    return new EmbeddedDatabaseBuilder().addScript("classpath:org/springframework/batch/core/schema-drop-hsqldb.sql")
      .addScript("classpath:org/springframework/batch/core/schema-hsqldb.sql").setType(EmbeddedDatabaseType.HSQL).build();
  }

  @Bean
  public BatchConfigurer batchConfigurer() {
    return new DefaultBatchConfigurer() {
      @Override
      public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
      }

      @Override
      protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(batchDataSource());
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
      }
    };
  }

}
