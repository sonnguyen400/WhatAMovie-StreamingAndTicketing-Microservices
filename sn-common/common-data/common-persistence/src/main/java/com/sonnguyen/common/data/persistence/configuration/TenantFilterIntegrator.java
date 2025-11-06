package com.sonnguyen.common.data.persistence.configuration;

import com.sonnguyen.common.data.persistence.entity.TenancyEntity;
import com.sonnguyen.common.web.security.SecurityUtils;
import org.hibernate.Session;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class TenantFilterIntegrator implements LoadEventListener, PreLoadEventListener {

    private void applyTenantFilter(Session session) {
        UUID tenantId = SecurityUtils.getTenantId();

        if (Objects.nonNull(tenantId)) {
            if (session.getEnabledFilter("tenantFilter") == null) {
                session.enableFilter("tenantFilter")
                        .setParameter("tenantId", tenantId);
            }
        } else {
            if (session.getEnabledFilter("tenantFilter") != null) {
                session.disableFilter("tenantFilter");
            }
        }
    }

    @Override
    public void onLoad(LoadEvent event, LoadType loadType) {
        if (Objects.equals(event.getEntityClassName(), TenancyEntity.class.getName())) {
            applyTenantFilter(event.getSession());
        }
    }

    @Override
    public void onPreLoad(PreLoadEvent event) {
        if (event.getEntity() instanceof TenancyEntity) {
            applyTenantFilter(event.getSession());
        }
    }

    // Các phương thức khác của Listener...
}
