package com.fullstackpeng.common.data.jpa.tenant.autoconfigure;

import com.fullstackpeng.common.data.jpa.tenant.configuration.DiscriminatorConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        DiscriminatorConfiguration.class
})
public class DataTenantAutoConfiguration {
}
