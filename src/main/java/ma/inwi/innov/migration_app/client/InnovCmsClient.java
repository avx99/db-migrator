package ma.inwi.innov.migration_app.client;


import org.springframework.cloud.openfeign.FeignClient;

/**
 * A Feign client interface for communicating with the Innov CMS API.
 * This client is used to interact with the Innov CMS system, which does not require any security or authentication.
 *
 * <p>Configuration:
 * The URL for the Innov CMS API is dynamically configured through the {@code feign.clients.innov.cms} property in the application's configuration files.
 * This allows the client to easily switch between different environments (e.g., development, staging, production) by changing the configuration without modifying the code.
 *
 * <p>Usage:
 * <pre>
 * @FeignClient(name = "InnovCmsClient", url = "${feign.clients.innov.cms}")
 * public interface InnovCmsClient {
 *     // Define methods to interact with Innov CMS API
 * }
 * </pre>
 * The client will automatically make HTTP requests to the Innov CMS API as defined by the methods in the interface.
 *
 * <p>Dependencies:
 * <ul>
 *     <li>Uses Spring Cloud Feign to facilitate communication with external HTTP APIs.</li>
 *     <li>The base URL of the API is retrieved from the configuration file using the property {@code feign.clients.innov.cms}.</li>
 * </ul>
 */
@FeignClient(
        name = "InnovCmsClient",
        url = "${feign.clients.innov.cms}"
)
public interface InnovCmsClient {
}
