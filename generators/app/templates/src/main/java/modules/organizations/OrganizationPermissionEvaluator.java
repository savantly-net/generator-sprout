package <%=groupId%>.modules.organizations;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import <%=groupId%>.security.Permission;
import <%=groupId%>.security.SproutPermissionEvaluator;

@Service
public class OrganizationPermissionEvaluator implements SproutPermissionEvaluator<Organization> {
    
    @Override
    public boolean hasPermission(Authentication authentication, Organization targetDomainObject, Permission permission) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Permission permission) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Class<Organization> getEvaluationType() {
        return Organization.class;
    }

}
