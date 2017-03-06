package <%=groupId%>.modules.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import <%=groupId%>.modules.emailAddress.EmailAddress;
import <%=groupId%>.modules.emailAddress.repository.EmailAddressRepository;
import <%=groupId%>.modules.organizations.OrganizationFixture;
import <%=groupId%>.modules.organizations.OrganizationRepository;
import <%=groupId%>.modules.roles.Role;
import <%=groupId%>.modules.roles.RoleRepository;
import <%=groupId%>.modules.user.repository.UserRepository;
import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

@Service
public class UserFixture extends AbstractBaseFixture<SproutUser, UserRepository>{

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private OrganizationRepository orgs;
    @Autowired
    private OrganizationFixture orgFixture;
    @Autowired
    private EmailAddressRepository emailAddressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Fixture<Role> roleFixture;
    @Autowired
    private Fixture<EmailAddress> emailFixture;
    private UserRepository repository;

    private String password = "password";
 
    @Autowired
    public UserFixture(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void addEntities(List<SproutUser> entityList) {
        addAdminUser(entityList);
    }

    private void addAdminUser(List<SproutUser> entityList) {
        String username = "admin";
        SproutUser userDetails = this.repository.findOneByUsername(username);
        
        if(userDetails != null) return;
        
        List<Role> authorities = new ArrayList<Role>(1);
        authorities.add(roleRepository.findOne("ADMIN"));
        userDetails = new SproutUser(username, password , "Admin", "User", authorities);
        userDetails.setPassword(encoder.encode(password));
        
        EmailAddress emailAddress =  emailAddressRepository.findOne("admin@example.com");
        userDetails.setPrimaryEmailAddress(emailAddress);
        entityList.add(userDetails);
    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {
        dependencies.add(roleFixture);
        dependencies.add(emailFixture);
        dependencies.add(orgFixture);
    }

}
