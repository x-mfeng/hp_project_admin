package com.lance.hp.hp_study.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName DataSourceConfig.java
 * @Description 配置数据源类
 * @createTime 2022/05/30 09:25:00
 */
@Configuration
/*扫描mapper的java包*/
@MapperScan("com.lance.hp.hp_study.mapper")
public class DataSourceConfig {
    @Bean(name="gymDataSource")
    @ConfigurationProperties("spring.datasource.gym")
    public DataSource gymDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean("gymSqlSessionFactory")
    public SqlSessionFactory systemSqlSessionFactory(@Qualifier("gymDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(matchMapperLocations());
        return mybatisSqlSessionFactoryBean.getObject();
    }
    /*可以省写application.yml下 mybatis classpath配置*/
    private Resource[] matchMapperLocations() throws IOException {
        return new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml");
    }
    @Bean("gymSqlSessionTemplate")
    public SqlSessionTemplate ImapSqlSessionTemplate(@Qualifier("gymSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
