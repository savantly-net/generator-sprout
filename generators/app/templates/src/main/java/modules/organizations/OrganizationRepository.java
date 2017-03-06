package <%=groupId%>.modules.organizations;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import <%=groupId%>.modules.PersistedModuleRepository;

@RepositoryRestResource(path="organizations", collectionResourceRel="organizations")
public interface OrganizationRepository extends PersistedModuleRepository<Organization> {
    
    Organization findOneByName(@Param("name") String name);

}
