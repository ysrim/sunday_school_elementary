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
// 1. 상위 패키지를 지정하면 하위 매퍼들을 알아서 찾습니다.
// 명확성을 위해 특정 패키지들만 스캔하도록 콤마로 구분하되 문자열 배열 형식을 권장합니다.
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

        // 2. HikariCP 성능 최적화
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(10);

        // 커넥션 최대 유지 시간 (DB 설정보다 30초 정도 짧게 잡는 것이 국룰)
        hikariConfig.setMaxLifetime(1800000); // 30분
        hikariConfig.setConnectionTimeout(30000); // 30초

        // 추가하신 Health Check 설정 (유지)
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setKeepaliveTime(300000);
        hikariConfig.setValidationTimeout(3000);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // 3. 리소스 패턴 로더 활용
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:spring/sqlmap/mapper/**/*.xml"));

        // 4. DTO 패키지 별칭 (여러 패키지를 콤마로 나열 가능)
        // 실제 프로젝트 구조에 맞게 수정하세요. (app..vo 형태로 와일드카드 사용 가능)

        // 5. MyBatis 상세 설정
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        // 쿼리 결과가 없을 때 빈 리스트가 아닌 null을 반환하는 설정 방지 등 추가 가능
        configuration.setJdbcTypeForNull(org.apache.ibatis.type.JdbcType.NULL);

        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}