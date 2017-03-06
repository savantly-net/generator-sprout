package <%=groupId%>.modules.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import <%=groupId%>.modules.user.SproutUser;

@RepositoryRestResource(path="users", collectionResourceRel="users")
public interface UserRepository extends PagingAndSortingRepository<SproutUser, String> {

	SproutUser findOneByUsername(String username);
	SproutUser findByPrimaryEmailAddress_EmailAddress(String emailAddress);
}
