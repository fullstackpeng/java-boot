package com.fullstackpeng.common.data.jpa.tenant.configuration;

import com.fullstackpeng.common.data.jpa.tenant.hibernate.DiscriminatorTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DiscriminatorConfiguration {

    @Bean
    public HibernatePropertiesCustomizer discriminatorTenantIdentifierResolver() {
        return new DiscriminatorTenantIdentifierResolver();
    }
}
