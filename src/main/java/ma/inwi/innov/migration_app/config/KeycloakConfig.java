package ma.inwi.innov.migration_app.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Configuration class for Keycloak integration with the application.
 * This class provides a bean that configures a {@link Keycloak} client using the provided {@link KeycloakProperties}.
 *
 * <p>The Keycloak client is set up with the necessary authentication details including the server URL, realm, client ID,
 * and user credentials. It uses the {@link KeycloakBuilder} to build and configure the client for authentication.
 *
 * <p>The Keycloak configuration class is designed to integrate with the application's authentication mechanism.
 * The class is annotated with {@link Configuration} to be detected by Spring as a source of bean definitions, and
 * it uses {@link RequiredArgsConstructor} to automatically generate a constructor that injects the {@link KeycloakProperties}.
 *
 * <p>Keycloak client is configured with the "password" grant type for resource owner password credentials flow.
 *
 * <p>Usage:
 * The Keycloak client can be used for authenticating users and interacting with the Keycloak authentication server.
 * Example usage:
 * <pre>
 * @Configuration
 * @RequiredArgsConstructor
 * public class KeycloakConfig {
 *     private final KeycloakProperties keycloakProperties;
 *
 *     @Bean
 *     @Lazy
 *     public Keycloak keycloak() {
 *         return KeycloakBuilder.builder()
 *                 .serverUrl(keycloakProperties.getAuthServerUrl())
 *                 .realm(keycloakProperties.getRealm())
 *                 .clientId(keycloakProperties.getResource())
 *                 .clientSecret(keycloakProperties.getCredentials().getSecret())
 *                 .password(keycloakProperties.getCredentials().getPassword())
 *                 .username(keycloakProperties.getCredentials().getUsername())
 *                 .grantType(OAuth2Constants.PASSWORD)
 *                 .build();
 *     }
 * }
 * </pre>
 *
 * <p>Dependencies:
 * <ul>
 *     <li>{@link KeycloakBuilder}: Used to create and configure the Keycloak client.</li>
 *     <li>{@link OAuth2Constants}: Provides constants related to OAuth2, such as the grant type for password flow.</li>
 * </ul>
 */
@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    private final KeycloakProperties keycloakProperties;

    /**
     * Bean that creates and configures a {@link Keycloak} client instance for authentication.
     *
     * <p>The Keycloak client is configured with the following parameters:
     * <ul>
     *     <li>Server URL: Retrieved from {@link KeycloakProperties#getAuthServerUrl()}</li>
     *     <li>Realm: Retrieved from {@link KeycloakProperties#getRealm()}</li>
     *     <li>Client ID: Retrieved from {@link KeycloakProperties#getResource()}</li>
     *     <li>Client Secret: Retrieved from {@link KeycloakProperties#getCredentials().getSecret()}</li>
     *     <li>Username and Password: Retrieved from {@link KeycloakProperties#getCredentials().getUsername()} and
     *         {@link KeycloakProperties#getCredentials().getPassword()}</li>
     *     <li>Grant Type: Set to {@link OAuth2Constants#PASSWORD} for resource owner password credentials flow.</li>
     * </ul>
     *
     * @return A configured {@link Keycloak} client instance.
     */
    @Bean
    @Lazy
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getResource())
                .clientSecret(keycloakProperties.getCredentials().getSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
