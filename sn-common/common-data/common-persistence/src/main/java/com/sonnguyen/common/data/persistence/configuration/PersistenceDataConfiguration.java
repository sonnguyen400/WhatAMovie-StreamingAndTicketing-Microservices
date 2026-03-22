package com.sonnguyen.common.data.persistence.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.sonnguyen.common.data.persistence.entityrepository",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*\\.persistence\\..*"
        ),
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*\\.document\\..*"
        )
)
@ConditionalOnClass(name = "org.springframework.data.mongodb.core.MongoTemplate")
@ComponentScan(basePackages = "com.sonnguyen.common.data.persistence")
public class PersistenceDataConfiguration {
}
