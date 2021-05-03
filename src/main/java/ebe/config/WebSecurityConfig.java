package ebe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests()
                .antMatchers( "/ebe/oauth2/**", "/ebe/login/**","/actuator/health" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/ebe/login/oauth2/code/*")
                .and()
//                .loginPage("/ebe/login")
        	    .authorizationEndpoint()
                .baseUri("/ebe/oauth2/authorization")
//                .defaultSuccessUrl("/")
                .authorizationRequestRepository(authorizationRequestRepository())
                .and()
                .defaultSuccessUrl("/ebe/")
//                .failureUrl("/ebe/");
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/ebe/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)        // set invalidation state when logout
                .deleteCookies("SESSION");



    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest>
    authorizationRequestRepository() {

        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }
//
//    @Bean
//    public AuthenticationSuccessHandler authSuccessHandler(){
//        return new AuthSuccessHandler();
//    }
}


//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    private final DataSource dataSource;
////
////    public WebSecurityConfig(DataSource dataSource) {
////        this.dataSource = dataSource;
////    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authSuccessHandler(){
//        return new AuthSuccessHandler();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.headers().frameOptions().disable();
//
////        http.csrf().disable();
//
//        //"/api/get/*","/api","/api/*"
//        http.authorizeRequests().antMatchers("*/*", "*", "*/*/*", "/api/*/*", "/api/*/*/*",  "/api/*/*/*/*", "/login", "/media/hodge-bank-logo.png", "/css/bootstrap/css/bootstrap.css", "static/css/eventPage.css", "./css/eventPage.css", "/css/header_footer.css", "/css/loginTemplate.css").permitAll();
//
//
////        http.authorizeRequests().anyRequest().authenticated().and().formLogin()
//////                .loginPage("/login")
//////                .defaultSuccessUrl("/Calendar")
////                .failureUrl("/login?error")
//////                .usernameParameter("username")
//////                .passwordParameter("password")
////                .successHandler(authSuccessHandler());
////
////
////        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource);
//    }
//}