//package com.sonnguyen.common.data.clickhouse;
//
//import org.springframework.data.repository.config.RepositoryConfigurationExtension;
//import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
//
//public class ClickHouseRepositoryConfigurationExtension extends RepositoryConfigurationExtensionSupport {
//
//    @Override
//    public String getModuleName() {
//        return "ClickHouse";
//    }
//
//    @Override
//    protected String getModulePrefix() {
//        return "clickhouse";
//    }
//
//    @Override
//    public String getRepositoryFactoryBeanClassName() {
//        return ClickHouseRepositoryFactoryBean.class.getName();
//    }
//
//    @Override
//    public String getDefaultNamedQueryLocation() {
//        return "META-INF/clickhouse-named-queries.properties";
//    }
//}
