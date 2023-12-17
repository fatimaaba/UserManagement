package ir.manage.manageofusers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/oauth2/token/**").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/openapi/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/error/**").permitAll()
                .requestMatchers("/actuator/health/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user-service/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/dept-service/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/organization-service/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/oauth2-redirect.html").permitAll()

                .requestMatchers(HttpMethod.POST, "/api/v1/users/add").hasRole("client_admin")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/filter").hasRole("client_user")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/filter").hasRole("client_admin")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/getuser/{email}").hasRole("client_admin")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/getuser/{email}").hasRole("client_user")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/delete/{externalId}").hasRole("client_admin")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users/update").hasRole("client_admin")

                .requestMatchers(HttpMethod.POST, "/api/v1/managers/add").hasRole("client_admin")
                .requestMatchers(HttpMethod.GET, "/api/v1/managers/filter").hasRole("client_admin")
                .requestMatchers(HttpMethod.GET, "/api/v1/managers/getmanager/{nationalCode}").hasRole("client_admin")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/managers/delete/{externalId}").hasRole("client_admin")
                .requestMatchers(HttpMethod.PUT, "/api/v1/managers/update").hasRole("client_admin")


                .anyRequest()
                .authenticated();

        http
                .oauth2ResourceServer()
                .jwt();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}