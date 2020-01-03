package com.sane.dh.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.sane.dh.dao.AuthoritiesMapper;
import com.sane.dh.filters.VerificationCodeFilter;
import com.sane.dh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    @Qualifier(value = "persistentTokenRepositoryImpl")
    private PersistentTokenRepository persistentTokenRepository;
    @Autowired
    private UserService userDetailsService;
    @Value("${jdbc.queryUser}")
    private String QUERY_USER_SQL;
    @Value("${jdbc.queryRoles}")
    private String QUERY_USER_ROLE_SQL;
    @Value("${jdbc.salt}")
    private String SALT_STR;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
        http.authorizeRequests()
                .antMatchers("/","/toUploadPage","/upload").authenticated().anyRequest().permitAll()
                .and().formLogin()
                .loginProcessingUrl("/doLogin").loginPage("/loginPage").failureForwardUrl("/loginerror").permitAll().and()
                .csrf().disable().rememberMe().key(SALT_STR).userDetailsService(userDetailsService).tokenRepository(persistentTokenRepository);
        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService);
//    }
    @Bean
    public Producer captha(){
        Properties properties=new Properties();
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH,"120");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT,"50");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,"abcdefghijklmnopqrstuvwxyz1234567890");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,"4");
        Config config=new Config(properties);
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
