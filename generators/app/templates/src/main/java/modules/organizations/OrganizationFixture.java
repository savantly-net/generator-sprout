package <%=groupId%>.modules.organizations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;

import <%=groupId%>.security.FakeContext;
import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

@Service
public class OrganizationFixture extends AbstractBaseFixture<Organization, OrganizationRepository>{
    
    @Autowired
    FakeContext context;

    private OrganizationRepository repository;

    public OrganizationFixture(OrganizationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void addEntities(List<Organization> entityList) {
        context.create();
        String orgName = "INTNT";
        Organization org = repository.findOneByName(orgName);
        if(org == null){
            this.repository.save(new Organization(orgName));
        }
    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {
        // TODO Auto-generated method stub
        
    }

}
