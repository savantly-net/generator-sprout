package <%=groupId%>.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import <%=groupId%>.security.SproutPasswordEncoder;

@Configuration("encryptionConfiguration")
public class EncryptionConfiguration {

    private PasswordEncoder passwordEncoder = new SproutPasswordEncoder();
    
    @Bean("passwordEncoder")
    public PasswordEncoder getPasswordEncoder(){
        return passwordEncoder;
    }
}
