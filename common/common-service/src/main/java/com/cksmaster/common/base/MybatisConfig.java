package com.cksmaster.common.base;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 在没有spring-boot-druid -starter之前需要这个config来使用
 * @author cks
 * @Date 2017/7/19.
 */
//@Configuration
//@EnableTransactionManagement
//@ComponentScan
//@MapperScan("com.cksmaster.common.mapper")
public class MybatisConfig {

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(MybatisConfig.class);


//    @Value("${spring.datasource.druid.type}")
//    private Class<? extends DataSource> dataSourceType;


//    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSource dataSource() {
//        logger.info("-------------------- writeDataSource init ---------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.cksmaster.common.mapper");
////        sqlSessionFactoryBean.setPlugins("com.github.pagehelper.PageHelper");
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
//        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        return sqlSessionFactoryBean.getObject();
//    }

//    /**
//     * 配置事务管理器
//     */
//    @Bean(name = "transactionManager")
//    @Primary
//    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }


}