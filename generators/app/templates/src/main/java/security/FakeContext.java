package <%=groupId%>.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import <%=groupId%>.modules.roles.Role;
import <%=groupId%>.modules.user.SproutUser;

public class FakeContext {
    
    private final Authentication auth;

    public FakeContext() {
        List<Role> roles = new ArrayList<Role>();
        SproutUser user = new SproutUser("admin", "password", "firstName", "lastName", roles);
        roles.add(new Role("ADMIN"));
        auth = new Authentication() {
            
            @Override
            public String getName() { return user.getUsername(); }
            
            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}
            
            @Override
            public boolean isAuthenticated() {return true;}
            
            @Override
            public Object getPrincipal() {return user;}
            
            @Override
            public Object getDetails() { return user; }
            
            @Override
            public Object getCredentials() { return null; }
            
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {return user.getAuthorities();}
        };
    }

    public void create() {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
