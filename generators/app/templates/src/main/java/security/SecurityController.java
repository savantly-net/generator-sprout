package <%=groupId%>.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class SecurityController {
    
    @RequestMapping(value = { "/token" }, method = RequestMethod.GET)
    public Authentication currentToken() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
