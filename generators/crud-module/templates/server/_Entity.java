package <%= groupId %>.modules.<%= camelizedPluralName %>;

import javax.persistence.Entity;

import <%= groupId %>.modules.PersistedModule;

@Entity
public class <%= classifiedSingularName %> extends PersistedModule {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
