package <%= groupId %>.modules.<%= camelizedPluralName %>;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface <%= classifiedSingularName %>Repository extends PagingAndSortingRepository<<%= classifiedSingularName %>, String> {

}
