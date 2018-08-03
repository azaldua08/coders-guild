package com.magenic.gamify.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static String REALM="CODERS_REALM";
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	    
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth.authenticationProvider(authenticationProvider());
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST)
        .hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE)
        .hasRole("ADMIN")
        .anyRequest().authenticated()
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
    }
    
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	 final CorsConfiguration configuration = new CorsConfiguration();
         configuration.setAllowedOrigins(Collections.unmodifiableList(Arrays.asList("*")));
         configuration.setAllowedMethods(Collections.unmodifiableList(Arrays.asList("HEAD",
                 "GET", "POST", "PUT", "DELETE", "PATCH")));
         // setAllowCredentials(true) is important, otherwise:
         // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
         configuration.setAllowCredentials(true);
         // setAllowedHeaders is important! Without it, OPTIONS preflight request
         // will fail with 403 Invalid CORS request
         configuration.setAllowedHeaders(Collections.unmodifiableList(Arrays
        		 .asList("Authorization", "Cache-Control", "Content-Type")));
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
          = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
     
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
