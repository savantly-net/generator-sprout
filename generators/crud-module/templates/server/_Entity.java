package <%= groupId %>.modules.<%= camelizedPluralName %>;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.intnt.modules.<%= camelizedPluralName %>.<%= classifiedSingularName %>Listener;

import <%= groupId %>.modules.PersistedModule;

@Entity
@EntityListeners({
    AuditingEntityListener.class,
    <%= classifiedSingularName %>Listener.class})
public class <%= classifiedSingularName %> extends PersistedModule {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
