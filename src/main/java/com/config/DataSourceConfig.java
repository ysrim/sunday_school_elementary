package com.config;

import javax.sql.DataSource;

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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:spring/prop/db.properties")
@EnableTransactionManagement
@MapperScan(value = {"app.idx.lgn.mapper" // 와일드카드 못쓴다해서 이렇게 일일히 선언
	+ ",app.idx.man.mapper" //
	+ ",app.idx.qrc.mapper" //
	+ ",app.idx.reg.mapper" //
	+ ",app.psn.com.mapper" //
	+ ",app.psn.stu.mapper" //
})
public class DataSourceConfig {

	@Value("${jdbc.driver}")
	String driver;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String username;
	@Value("${jdbc.password}")
	String password;

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);

		// 커넥션 풀 튜닝
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);

		// -------------------------------------------------------
		// [추가 설정] Health Check & Keep Alive
		// -------------------------------------------------------

		// 1. 검증용 쿼리 지정 (SELECT 1)
		// 최신 드라이버는 생략해도 알아서 ping을 날리지만, 명시하고 싶다면 아래처럼 씁니다.
		hikariConfig.setConnectionTestQuery("SELECT 1");

		// 2. 주기적 실행 간격 (Keepalive Time)
		// 설정한 시간마다 커넥션이 살아있는지 확인합니다. (단위: ms)
		// DB나 방화벽의 타임아웃보다 짧게 설정해야 끊김을 방지합니다.
		// 예: 5분(300000ms)마다 SELECT 1 실행
		hikariConfig.setKeepaliveTime(300000);

		// 3. 검증 타임아웃 (선택)
		// SELECT 1 쿼리가 3초 안에 응답 없으면 연결 끊긴 걸로 간주하고 재연결 시도
		hikariConfig.setValidationTimeout(3000);

		return new HikariDataSource(hikariConfig);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		// 1. Mapper XML 위치 지정
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:spring/sqlmap/mapper/**/*.xml"));

		// 2. DTO 패키지 별칭 지정
		sessionFactory.setTypeAliasesPackage("com.example.domain");

		// 3. MyBatis 설정 (camelCase 등)
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true); // 스네이크->카멜 자동 변환
		configuration.setCallSettersOnNulls(true);
		sessionFactory.setConfiguration(configuration);

		return sessionFactory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}