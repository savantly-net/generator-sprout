package <%=groupId%>.modules.emailAddress;

import java.util.List;

import org.springframework.stereotype.Service;

import <%=groupId%>.modules.emailAddress.repository.EmailAddressRepository;
import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

@Service
public class EmailAddressFixture extends AbstractBaseFixture<EmailAddress, EmailAddressRepository> {

    private EmailAddressRepository repository;

    public EmailAddressFixture(EmailAddressRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void addEntities(List<EmailAddress> entityList) {
        String adminEmail = "admin@example";
        if(repository.findOne(adminEmail) == null){
            entityList.add(new EmailAddress(adminEmail));
        }
    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {
        // TODO Auto-generated method stub
        
    }

}