package <%= groupId %>.modules.<%= slugifiedPluralName %>;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface <%= capitalizedSingularName %>Repository extends PagingAndSortingRepository<<%= capitalizedSingularName %>, UUID> {

}
