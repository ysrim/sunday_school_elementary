package com.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:spring/prop/db.properties") // DB 프로퍼티 로드
@EnableTransactionManagement // @Transactional 활성화
public class DataSourceConfig {

	// DB 관련 프로퍼티 빈
	@Bean(name = "dbProps")
	public PropertiesFactoryBean dbProps() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("spring/prop/db.properties"));
		return bean;
	}

	// @Value를 사용하기 위한 설정 (static 필수)
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// Datasource 관련 변수 (@Value)
	@Value("${DataBase.Maria.DriverClassName}")
	private String dbDrvClzNm;

	@Value("${DataBase.home.Url}")
	private String dbUrl;

	@Value("${DataBase.home.UserName}")
	private String dbUserNm;

	@Value("${DataBase.home.Password}")
	private String dbPwd;

	// Alias 처리를 위해 name 배열 사용: dataSource-home, home.dataSource
	@Bean(name = {"dataSource-home", "home.dataSource"}, destroyMethod = "close")
	public DataSource dataSourceHome() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbDrvClzNm);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUserNm);
		dataSource.setPassword(dbPwd);
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		// home.dataSource 빈을 주입받아 트랜잭션 매니저 생성
		return new DataSourceTransactionManager(dataSourceHome());
	}

	@Bean(name = "home.sqlSession")
	public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSourceHome());

		// configLocation 설정
		sessionFactoryBean.setConfigLocation(new ClassPathResource("spring/sqlmap/config/sql-map-config.xml"));

		// mapperLocations 설정 (와일드카드 패턴 지원을 위해 PathMatchingResourcePatternResolver 사용)
		sessionFactoryBean.setMapperLocations(
			new PathMatchingResourcePatternResolver().getResources("classpath:spring/sqlmap/mapper/**/*.xml")
		);

		return sessionFactoryBean.getObject();
	}
}