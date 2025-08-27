package com.ofb.sbatch.consents.expiration.control.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource dataSourceMySQL() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource dataSourceOracle() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSourceOracle") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
