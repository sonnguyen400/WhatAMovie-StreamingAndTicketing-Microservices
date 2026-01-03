//package com.sonnguyen.common.data.clickhouse;
//
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
//import org.springframework.data.repository.core.support.RepositoryFactorySupport;
//
//public class ClickHouseRepositoryFactoryBean<T extends Repository<S, ID>, S, ID>
//       extends RepositoryFactoryBeanSupport<T, S, ID> {
//
//    private ClickHouseTemplate clickHouseTemplate;
//
//    public ClickHouseRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
//        super(repositoryInterface);
//    }
//
//    public void setClickHouseTemplate(ClickHouseTemplate clickHouseTemplate) {
//        this.clickHouseTemplate = clickHouseTemplate;
//    }
//
//    @Override
//    protected RepositoryFactorySupport createRepositoryFactory() {
//        return new ClickHouseRepositoryFactory(clickHouseTemplate);
//    }
//}
