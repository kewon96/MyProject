package com.myproject.config;

import com.myproject.api.MemberService;
import com.myproject.filter.JwtAuthenticationFilter;
import com.myproject.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    /** 암호화에 필요한 PasswordEncoder를 Bean에 등록 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /** authenticationManager를 Bean에 등록 */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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
//        http.httpBasic().disable();
//        http.csrf().disable(); // spa용 Could not verify the provided CSRF token
//        http.cors();
//        http.headers().frameOptions().sameOrigin();
//
//        http.authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 또한 사용하지 않음
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/admin/**").hasRole("ADMIN") // "/admin/**" 형식의 URL로 들어오는 요청에 대한 인증
                .antMatchers("/user/**").hasRole("USER")   // "/user/**"  형식의 URL로 들어오는 요청에 대한 인증
                .anyRequest().permitAll() // 그 외 나머지 요청은 누구나 접근 가능하게
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
        ;

    }


}
