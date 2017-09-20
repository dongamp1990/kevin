package org.kevin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
 
	@Bean
	@Primary
	@ConfigurationProperties("app.datasource")
	public DataSourceProperties dataSourceProperties() {
	    return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource")
	public HikariDataSource dataSource(DataSourceProperties properties) {
	    return (HikariDataSource) properties.initializeDataSourceBuilder()
	            .type(HikariDataSource.class).build();
	}
	
	@Bean(name = "jdbcTemplate")
	@Autowired
    public JdbcTemplate jdbcTemplate(DataSource ds) {
		System.out.println(ds);
        return new JdbcTemplate(ds); 
    }
}
