package bibliotroca.BiblioTroca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/login", "/logout").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/bibliotroca/livros").permitAll()
                                .anyRequest().authenticated())
                .oauth2Login(oauth ->
                        oauth.loginPage("/login"))
                .logout(logout ->
                        logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                                .logoutSuccessUrl("/login"));
        return http.build();
    }

}
