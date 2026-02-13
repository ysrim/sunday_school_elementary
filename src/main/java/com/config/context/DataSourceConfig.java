package com.config.context;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:spring/prop/db.properties")
@EnableTransactionManagement
@MapperScan(basePackages = {"app.**.mapper"})
public class DataSourceConfig {

	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource dataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);

		// HikariCP 성능 최적화
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(10);

		// 커넥션 최대 유지 시간
		hikariConfig.setMaxLifetime(1800000); // 30분
		hikariConfig.setConnectionTimeout(30000); // 30초

		// Health Check 설정 (유지)
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setKeepaliveTime(300000);
		hikariConfig.setValidationTimeout(3000);

		return new HikariDataSource(hikariConfig);

	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		// 리소스 패턴 로더 활용
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:spring/sqlmap/mapper/**/*.xml"));

		// MyBatis 상세 설정
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCallSettersOnNulls(true);
		configuration.setJdbcTypeForNull(org.apache.ibatis.type.JdbcType.NULL); // 쿼리 결과가 없을 때 빈 리스트가 아닌 null을 반환하는 설정 방지 등 추가 가능
		sessionFactory.setConfiguration(configuration);

		return sessionFactory.getObject();

	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {

		return new DataSourceTransactionManager(dataSource);

	}

}