package com.myproject.config;

import com.myproject.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;

    /**
     * html, ts같은 static 자원에 대한 인증검사를 무시하게 하는 설정
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/", "/**/*.html", "/favicon.ico")
                .antMatchers("/app/**")//not minified
                .antMatchers("/scripts/**", "/styles/**", "/images/**")
                .antMatchers("/style*", "/vendor*", "/app*", "/templates*") //minified
        ;
    }

    /**
     * CORS 관련 설정
     * 현 환경은 Client, Server 분리가 되있기때문에 CORS관련 이슈가 발생하는데 이를 방지하기 위함
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.csrf().disable(); // spa용 Could not verify the provided CSRF token
        http.cors();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService);
    }


}
