package ma.inwi.innov.migration_app.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {
    private final KeycloakProperties keycloakProperties;

    @Bean
    @Lazy
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getResource())
                .clientSecret(keycloakProperties.getCredentials().getSecret())
                .password(keycloakProperties.getCredentials().getPassword())
                .username(keycloakProperties.getCredentials().getUsername())
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }
}
