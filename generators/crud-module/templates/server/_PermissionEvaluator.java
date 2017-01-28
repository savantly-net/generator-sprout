package <%= groupId %>.modules.<%= camelizedPluralName %>;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import co.intnt.security.Permission;
import co.intnt.security.SproutPermissionEvaluator;

@Service
public class <%= classifiedSingularName %>PermissionEvaluator implements SproutPermissionEvaluator<<%= classifiedSingularName %>> {
    
    @Override
    public boolean hasPermission(Authentication authentication, <%= classifiedSingularName %> targetDomainObject, Permission permission) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Permission permission) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Class<<%= classifiedSingularName %>> getEvaluationType() {
        return <%= classifiedSingularName %>.class;
    }

}
