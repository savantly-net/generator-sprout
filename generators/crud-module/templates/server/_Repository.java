package <%= groupId %>.modules.<%= camelizedPluralName %>;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import <%= groupId %>.modules.PersistedModuleRepository;

@RepositoryRestResource(path="/<%= slugifiedPluralName %>", collectionResourceRel="/<%= slugifiedPluralName %>")
public interface <%= classifiedSingularName %>Repository extends PersistedModuleRepository<<%= classifiedSingularName %>> {

}
