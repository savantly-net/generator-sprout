package <%=groupId%>.modules;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface PersistedModuleRepository<T> extends PagingAndSortingRepository<T, String>{

}
