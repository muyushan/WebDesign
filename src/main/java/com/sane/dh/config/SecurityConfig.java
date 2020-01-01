package com.sane.dh.config;

import com.sane.dh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
//    @Qualifier(value = "userServiceImpl")
    private UserService userDetailsService;
    @Value("${jdbc.queryUser}")
    private String QUERY_USER_SQL;
    @Value("${jdbc.queryRoles}")
    private String QUERY_USER_ROLE_SQL;
    @Value("${jdbc.salt}")
    private String SALT_STR;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/toUploadPage","/upload").authenticated().anyRequest().permitAll().and().formLogin().loginPage("/loginPage").permitAll().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(QUERY_USER_SQL).authoritiesByUsernameQuery(QUERY_USER_ROLE_SQL);
//                .passwordEncoder(new StandardPasswordEncoder(SALT_STR));
    }
}
