package simple.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.project.user.JWToken;

@Configuration
public class TokenConfig {
    @Bean
    public JWToken jwToken() {
        return new JWToken();
    }
}
