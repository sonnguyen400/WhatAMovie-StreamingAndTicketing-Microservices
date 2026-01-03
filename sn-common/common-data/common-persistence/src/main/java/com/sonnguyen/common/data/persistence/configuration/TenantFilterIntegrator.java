package com.sonnguyen.common.data.persistence.configuration;

import com.sonnguyen.common.data.persistence.entity.TenancyEntity;
import com.sonnguyen.common.model.application.security.UserAuthority;
import org.hibernate.Session;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class TenantFilterIntegrator implements LoadEventListener, PreLoadEventListener {

    private void applyTenantFilter(Session session) {
        UUID tenantId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UserAuthority){
            tenantId = ((UserAuthority) authentication).getTenantId();
        }

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
