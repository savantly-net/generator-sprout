package <%=groupId%>.modules.emailAddress.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import <%=groupId%>.modules.emailAddress.EmailAddress;

public interface EmailAddressRepository extends PagingAndSortingRepository<EmailAddress, String>, EmailAddressRepositoryCustom {


}
