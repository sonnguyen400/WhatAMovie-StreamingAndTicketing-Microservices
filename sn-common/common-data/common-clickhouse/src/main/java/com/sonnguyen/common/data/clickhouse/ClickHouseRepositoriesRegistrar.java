//package com.sonnguyen.common.data.clickhouse;
//
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.data.repository.config.RepositoryConfigurationExtension;
//import org.springframework.data.repository.config.RepositoryConfigurationUtils;
//
//public class ClickHouseRepositoriesRegistrar implements ImportBeanDefinitionRegistrar {
//
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
//                                      BeanDefinitionRegistry registry) {
//
//        RepositoryConfigurationExtension extension = new ClickHouseRepositoryConfigurationExtension();
//
//        RepositoryConfigurationUtils.exposeRegistration(extension, registry, getBasePackages(annotationMetadata));
//    }
//
//    private String[] getBasePackages(AnnotationMetadata annotationMetadata) {
//        // Logic để lấy base packages từ annotation
//        return new String[]{"com.example.repository"};
//    }
//}
