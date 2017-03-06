package <%=groupId%>.modules.user.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import <%=groupId%>.modules.user.SproutUser;

@Configuration
public class UserRepositoryConfiguration {

    public UserRepositoryConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(SproutUser.class);
    }
    
}
