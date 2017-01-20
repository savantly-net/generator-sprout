package <%= groupId %>.modules.<%= camelizedPluralName %>;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Configuration
public class <%= classifiedSingularName %>RepositoryConfiguration {
    
    public <%= classifiedSingularName %>RepositoryConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(<%= classifiedSingularName %>.class);
    }

}
