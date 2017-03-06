package <%= groupId %>.modules.<%= camelizedPluralName %>;

import javax.persistence.PrePersist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Service;

import <%= groupId %>.modules.<%= camelizedPluralName %>.<%= classifiedSingularName %>;

/**
 * Override event methods in superclass
 */
@Service
public class <%= classifiedSingularName %>Listener extends AbstractRepositoryEventListener<<%= classifiedSingularName %>>{
    
    private final Logger log = LoggerFactory.getLogger(<%= classifiedSingularName %>Listener.class);
    
    @Override
    protected void onBeforeSave(<%= classifiedSingularName %> entity){
        
    }

}