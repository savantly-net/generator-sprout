package <%= groupId %>.modules.<%= camelizedPluralName %>;

import java.util.List;

import org.springframework.stereotype.Service;

import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

@Service
public class <%= classifiedSingularName %>Fixture extends AbstractBaseFixture<<%= classifiedSingularName %>, <%= classifiedSingularName %>Repository>{

    public OrganizationFixture(<%= classifiedSingularName %>Repository repository) {
        super(repository);
    }

    @Override
    public void addEntities(List<<%= classifiedSingularName %>> entityList) {

    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {

    }

}
