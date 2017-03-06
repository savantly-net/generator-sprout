package <%=groupId%>.modules.user.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import <%=groupId%>.modules.user.SproutUser;

public interface SproutUserDetailsService extends UserDetailsService{

    SproutUser loadByEmailAddress(String emailAddress);
    
}
