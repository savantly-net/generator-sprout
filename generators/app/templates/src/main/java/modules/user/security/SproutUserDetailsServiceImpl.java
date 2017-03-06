package <%=groupId%>.modules.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import <%=groupId%>.modules.emailAddress.EmailAddress;
import <%=groupId%>.modules.emailAddress.repository.EmailAddressRepository;
import <%=groupId%>.modules.user.SproutUser;
import <%=groupId%>.modules.user.repository.UserRepository;

@Component("userDetailsService")
public class SproutUserDetailsServiceImpl implements SproutUserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailAddressRepository emailAddressRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SproutUser user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not found: %s", username));
        } else {
            return user;
        }
    }

    @Override
    public SproutUser loadByEmailAddress(String emailAddress) {
        EmailAddress email = emailAddressRepository.findOne(emailAddress);
        return email.getUser();
    }

}
