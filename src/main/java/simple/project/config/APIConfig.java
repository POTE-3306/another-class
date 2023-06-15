package simple.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.project.user.JWToken;
import simple.project.user.NaverAPI;

@Configuration
public class APIConfig {
    @Bean
    public NaverAPI naverAPI() {
        return new NaverAPI();
    }

    @Bean
    public JWToken jwToken() {
        return new JWToken();
    }
}
