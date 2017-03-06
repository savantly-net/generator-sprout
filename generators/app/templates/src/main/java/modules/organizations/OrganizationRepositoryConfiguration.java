package <%=groupId%>.modules.organizations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Configuration
public class OrganizationRepositoryConfiguration {
    
    public OrganizationRepositoryConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(Organization.class);
    }

}
