package <%= groupId %>.modules.<%= camelizedPluralName %>;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="/<%= slugifiedPluralName %>", collectionResourceRel="/<%= slugifiedPluralName %>")
public interface <%= classifiedSingularName %>Repository extends PersistedModuleRepository<%= classifiedSingularName %> {

}
