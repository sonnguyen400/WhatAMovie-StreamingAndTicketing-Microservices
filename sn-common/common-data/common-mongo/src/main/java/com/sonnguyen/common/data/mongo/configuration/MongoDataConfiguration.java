package com.sonnguyen.common.data.mongo.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*\\.document\\..*"
        ),
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*\\.persistence\\..*"
        )
)
@ConditionalOnClass(name = "jakarta.persistence.EntityManager")
@ComponentScan(basePackages = "com.sonnguyen.common.data.mongo")
public class MongoDataConfiguration {
}
