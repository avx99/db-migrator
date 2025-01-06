package ma.inwi.innov.migration_app.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {
    private String authServerUrl;
    private String realm;
    private String resource;
    private String username;
    private String password;
    private Credentials credentials;
    @Data
    public static class Credentials {
        private String secret;
        private String username;
        private String password;
    }
}
