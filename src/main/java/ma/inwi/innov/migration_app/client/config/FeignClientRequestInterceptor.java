package ma.inwi.innov.migration_app.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import ma.inwi.innov.migration_app.service.KeycloakUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * A configuration class that provides a {@link RequestInterceptor} bean for Feign clients.
 * The interceptor is responsible for adding an Authorization header to each Feign request
 * by retrieving a valid access token from the {@link KeycloakUserService}.
 *
 * <p>This ensures that each request made by Feign clients to external services (e.g., Innov service)
 * is properly authenticated using the Bearer token obtained from Keycloak. The token is retrieved
 * dynamically before sending the request, ensuring that requests are always made with the latest valid token.
 *
 * <p>Usage:
 * <pre>
 * @FeignClient(name = "innov-service")
 * public interface InnovServiceClient {
 *     // Define methods that communicate with the Innov service
 * }
 * </pre>
 * The Feign client will automatically use the {@link RequestInterceptor} to include the Authorization header.
 *
 * <p>Dependencies:
 * <ul>
 *     <li>Uses {@link KeycloakUserService} to get the current access token from Keycloak.</li>
 *     <li>Relies on Feign for HTTP communication and Spring configuration for beans.</li>
 * </ul>
 */
@Configuration
@RequiredArgsConstructor
public class FeignClientRequestInterceptor {

    /**
     * The {@link KeycloakUserService} used to retrieve the access token.
     */
    private final KeycloakUserService keycloakUserService;

    /**
     * Provides a {@link RequestInterceptor} bean for Feign clients.
     * This interceptor adds an {@code Authorization} header with a Bearer token
     * retrieved from the {@link KeycloakUserService} for each outgoing Feign request.
     *
     * @return the {@link RequestInterceptor} bean to be used by Feign clients
     */
    @Bean
    RequestInterceptor requestInterceptor() {
        return (RequestTemplate template) -> {
            // Add the Authorization header with Bearer token
            template.headers().put("Authorization", List.of("Bearer " + keycloakUserService.getAccessToken()));
        };
    }
}

