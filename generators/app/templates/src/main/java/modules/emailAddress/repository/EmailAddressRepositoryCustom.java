package <%=groupId%>.modules.emailAddress.repository;

import java.util.Collection;
import java.util.List;

import <%=groupId%>.modules.emailAddress.EmailAddress;

public interface EmailAddressRepositoryCustom {
	List<EmailAddress> findOrInsert(Collection<EmailAddress> emailAddresses);
}
