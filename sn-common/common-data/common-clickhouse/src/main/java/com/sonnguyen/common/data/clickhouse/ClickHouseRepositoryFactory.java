//package com.sonnguyen.common.data.clickhouse;
//
//import org.springframework.data.repository.core.EntityInformation;
//import org.springframework.data.repository.core.RepositoryInformation;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.core.support.RepositoryFactorySupport;
//import org.springframework.data.repository.query.QueryLookupStrategy;
//import org.springframework.data.repository.query.QueryMethod;
//
//import java.lang.reflect.Method;
//import java.util.Optional;
//
//public class ClickHouseRepositoryFactory extends RepositoryFactorySupport {
//
//    private final ClickHouseTemplate clickHouseTemplate;
//    private final ClickHouseQueryLookupStrategy queryLookupStrategy;
//
//    public ClickHouseRepositoryFactory(ClickHouseTemplate clickHouseTemplate) {
//        this.clickHouseTemplate = clickHouseTemplate;
//        this.queryLookupStrategy = new ClickHouseQueryLookupStrategy(clickHouseTemplate);
//    }
//
//    @Override
//    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
//        return new ClickHouseEntityInformation<>(domainClass);
//    }
//
//    @Override
//    protected Object getTargetRepository(RepositoryInformation metadata) {
//        return new SimpleClickHouseRepository<>(
//            getEntityInformation(metadata.getDomainType()),
//            clickHouseTemplate
//        );
//    }
//
//    @Override
//    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//        return SimpleClickHouseRepository.class;
//    }
//
//    @Override
//    protected Optional<QueryLookupStrategy> getQueryLookupStrategy() {
//        return Optional.of(queryLookupStrategy);
//    }
//}
