package <%=groupId%>.modules.roles;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Configuration
public class RoleRepositoryConfiguration {
    
    public RoleRepositoryConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(Role.class);
    }

}
