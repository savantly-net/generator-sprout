package <%=groupId%>.security;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@NoRepositoryBean
public interface SecureRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>{
    
    @Override
    @PreAuthorize("hasPermission('DELETE')")
    void delete(ID id);
    
    @Override
    @PreAuthorize("hasPermission(#entity, 'SAVE')")
    <S extends T> S save(S entity);

}
