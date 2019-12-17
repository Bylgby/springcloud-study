package com.martin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库连接配置类
 * <br>
 * created date 2019/12/17 9:35
 *
 * @author maxiaowei
 */
@Configuration
@MapperScan(basePackages = "com.martin.mapper", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class DataBaseConfig {

    /**
     * 连接池属性
     */
    @Bean
    @ConfigurationProperties(prefix = "hihari.primary")
    public DataSourceBean primaryDataSourceBean() {
        return new DataSourceBean();
    }

    /**
     * 连接池
     */
    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource primaryDataSource() {
        DataSourceBean dataSource = primaryDataSourceBean();
        return dataSource.getDataSource();
    }

    /**
     * Mybatis sqlSession
     */
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    @ConditionalOnBean(name = {"primaryDataSource"})
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        bean.getObject().getConfiguration().setCallSettersOnNulls(true);
        return bean.getObject();
    }

    /**
     * 事务配置 声明式事务时 需要注明事务的bean 名称
     */
    @Primary
    @Bean(name = "transactionManager")
    @ConditionalOnBean(name = {"primaryDataSource"})
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
