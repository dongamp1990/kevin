package org.kevin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean("primaryDataSourceConf")
	@Qualifier("primaryDataSourceConf")
	@ConfigurationProperties("app.datasource.primary")
	public DataSourceProperties defaultDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("secondaryDataSourceConf")
	@Qualifier("secondaryDataSourceConf")
	@ConfigurationProperties("app.datasource.secondary")
	public DataSourceProperties otherDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "dataSource")
	@Qualifier("primaryDataSource")
	public HikariDataSource dataSource(
			@Qualifier("primaryDataSourceConf") DataSourceProperties properties) {
		return (HikariDataSource) properties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean(name = "dataSourceOther")
	@Qualifier("secondaryDataSource")
	public HikariDataSource dataSourceOther(
			@Qualifier("secondaryDataSourceConf") DataSourceProperties properties) {
		return (HikariDataSource) properties.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(
			@Qualifier("primaryDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean(name = "jdbcTemplateOther")
	@Qualifier("jdbcTemplateOther")
	public JdbcTemplate jdbcTemplateOther(
			@Qualifier("secondaryDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
