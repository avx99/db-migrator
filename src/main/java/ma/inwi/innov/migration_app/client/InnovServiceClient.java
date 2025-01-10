package ma.inwi.innov.migration_app.client;

import feign.Headers;
import ma.inwi.innov.migration_app.client.config.FeignClientRequestInterceptor;
import ma.inwi.innov.migration_app.enumeration.documents.DocumentType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Feign client interface for communicating with the Innov Service API.
 * This client provides functionality to upload documents to the Innov Service.
 * The client is configured with a {@link FeignClientRequestInterceptor} for adding the necessary authorization token to the requests.
 *
 * <p>Configuration:
 * The URL for the Innov Service API is retrieved from the {@code feign.clients.innov.service} property in the application's configuration files.
 * The client is automatically configured with a custom {@link FeignClientRequestInterceptor} that attaches an access token to the request headers.
 *
 * <p>Endpoints:
 * This interface defines a method for uploading a document to the Innov Service API.
 * <ul>
 *     <li>{@code uploadDocument} - Uploads a document with specified parameters.</li>
 * </ul>
 *
 * <p>Usage:
 * <pre>
 * @FeignClient(name = "innov-service", url = "${feign.clients.innov.service}", configuration = FeignClientRequestInterceptor.class)
 * public interface InnovServiceClient {
 *     @PostMapping(value = "/v1/documents/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
 *     @Headers("Content-Type: multipart/form-data")
 *     String uploadDocument(
 *             @RequestPart("file") MultipartFile file,
 *             @RequestParam DocumentType documentType,
 *             @RequestParam String entity);
 * }
 * </pre>
 *
 * <p>Dependencies:
 * <ul>
 *     <li>Uses Spring Cloud Feign to facilitate communication with external HTTP APIs.</li>
 *     <li>The client is configured with a custom {@link FeignClientRequestInterceptor} that adds an authorization token to the request headers.</li>
 *     <li>Supports file uploads with the {@code multipart/form-data} content type using the {@link MultipartFile} class.</li>
 * </ul>
 */
@FeignClient(
        name = "innov-service",
        url = "${feign.clients.innov.service}",
        configuration = FeignClientRequestInterceptor.class
)
public interface InnovServiceClient {

    /**
     * Uploads a document to the Innov Service API.
     * The document is sent as a multipart file, and additional parameters are passed as form data.
     *
     * @param file The document to upload.
     * @param documentType The type of the document being uploaded.
     * @param entity The entity to associate with the document.
     * @return A response indicating the result of the document upload.
     */
    @PostMapping(value = "/v1/documents/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    String uploadDocument(
            @RequestPart("file") MultipartFile file,
            @RequestParam DocumentType documentType,
            @RequestParam String entity);
}
