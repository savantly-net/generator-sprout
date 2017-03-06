package <%=groupId%>.modules.roles;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import <%=groupId%>.security.Permission;
import <%=groupId%>.security.SproutPermissionEvaluator;

@Service
public class RolePermissionEvaluator implements SproutPermissionEvaluator<Role> {
    
    @Override
    public boolean hasPermission(Authentication authentication, Role targetDomainObject, Permission permission) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Permission permission) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Class<Role> getEvaluationType() {
        return Role.class;
    }

}
