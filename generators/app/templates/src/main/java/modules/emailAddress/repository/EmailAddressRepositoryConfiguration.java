package <%=groupId%>.modules.emailAddress.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import <%=groupId%>.modules.emailAddress.EmailAddress;

@Configuration
public class EmailAddressRepositoryConfiguration {

    public EmailAddressRepositoryConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(EmailAddress.class);
    }
}
