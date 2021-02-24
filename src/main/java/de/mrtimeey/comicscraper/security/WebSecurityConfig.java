package de.mrtimeey.comicscraper.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ComicScraperAuthenticationEntryPoint comicScraperAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .and()
                .httpBasic().authenticationEntryPoint(comicScraperAuthenticationEntryPoint)
                .and()
                .csrf().disable();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                .allowedMethods("GET", "UPDATE", "POST","DELETE");
            }
        };
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
