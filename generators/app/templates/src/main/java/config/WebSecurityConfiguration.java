package <%=groupId%>.config;


import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).and().eraseCredentials(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/*.js", "/libs/**", "/modules/**", "**/favicon.ico");
        web.debug(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationEntryPoint authenticationEntryPoint = new DelegatingAuthenticationEntryPoint(entryPoints());

        http
                .headers().frameOptions().disable().and()
                .authorizeRequests()
                    .antMatchers("/", "/home").authenticated().and()
                .csrf().disable()
                .formLogin().loginPage("/login").permitAll();
                    //.loginProcessingUrl("/login").successHandler(successHandler()).and()
                //.logout().logoutSuccessHandler(logoutSuccessHandler()).permitAll().and()
                //.exceptionHandling().accessDeniedPage("/errors/403").authenticationEntryPoint(authenticationEntryPoint);
    }

    private LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints() {
        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>(1);
        entryPoints.put(restMatcher(), auth403());
        return entryPoints;
    }

    private AuthenticationEntryPoint auth403() {
        return new Http403ForbiddenEntryPoint();
    }

    private RequestMatcher restMatcher() {
        return new RegexRequestMatcher("/*", null);
    }

    LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
                res.setStatus(200);
            }
        };
    }

    AuthenticationSuccessHandler successHandler() {
        //return new SimpleUrlAuthenticationSuccessHandler("/security/token");
        return new SimpleUrlAuthenticationSuccessHandler("/#!/");
    }
    
}