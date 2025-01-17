package ma.inwi.innov.migration_app.service;


import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.config.KeycloakProperties;
import ma.inwi.innov.migration_app.dto.AccountDto;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing Keycloak users.
 * <p>
 * This service provides methods to create users in Keycloak, set user passwords,
 * and retrieve access tokens. It interacts with Keycloak's Admin REST API to manage users
 * and their credentials.
 * </p>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserService {

    private final KeycloakProperties keycloakProperties;

    @Lazy
    private final Keycloak keycloak;

    /**
     * Creates a new user in Keycloak.
     * <p>
     * This method maps the provided {@link AccountDto} to a {@link UserRepresentation},
     * and sends a request to Keycloak to create the user. If the user is successfully created,
     * it returns the created user's ID. If creation fails, an error message is logged.
     * </p>
     *
     * @param account The account details for the user to be created.
     * @param isActive Whether the user should be active.
     * @return The ID of the newly created user.
     */
    public String createUser(AccountDto account, Boolean isActive) {
        try {
            log.info("Start Service migrate user to keycloak");

            var user = mapToUserRepresentation(account, isActive);
            var response = keycloak.realm(keycloakProperties.getRealm()).users().create(user);

            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                var errorMessage = String.format("Failed to create user %s: %s", account.email(), response.readEntity(String.class));
                log.error(errorMessage);
            }

            var userId = extractUserId(response);
            log.info("End Service migrate user to keycloak");
            return userId;
        } catch (Exception e) {
            log.error("can not migrate the user {}", account.email());
            return "xxx-" + UUID.randomUUID().toString();
        }
    }

    /**
     * Delete user in Keycloak.
     *
     * @param keycloakId The user id to delete.
     */
    public void deleteUser(String keycloakId) {
        try {
            log.info("Start Service delete user from keycloak with id {}", keycloakId);
            var response = keycloak.realm(keycloakProperties.getRealm()).users().delete(keycloakId);
            if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
                log.error("Failed to delete user with id {}", keycloakId);
            }
        } catch (Exception e) {
            log.error("cannot delete user from keycloak with id {}", keycloakId);
        }
    }

    /**
     * Extracts the user ID from the location URL in the Keycloak response.
     *
     * @param response The response from the Keycloak server.
     * @return The user ID.
     */
    private String extractUserId(Response response) {
        var location = response.getLocation().getPath();
        return location.substring(location.lastIndexOf('/') + 1);
    }

    /**
     * Maps the account data to a Keycloak {@link UserRepresentation}.
     * <p>
     * This method creates a {@link UserRepresentation} object that contains the user data,
     * such as first name, last name, email, username, and credentials.
     * </p>
     *
     * @param account The account data.
     * @param isActive Whether the user is active.
     * @return The Keycloak {@link UserRepresentation}.
     */
    private UserRepresentation mapToUserRepresentation(AccountDto account, Boolean isActive) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setSecretData(account.password());
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(account.password() + "xxxxxxxxxxxxx");

        var userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(account.firstName() + "xxxxxxxxxxxxx");
        userRepresentation.setLastName(account.lastName() + "xxxxxxxxxxxxx");
        userRepresentation.setEmail(account.email());
        userRepresentation.setUsername(account.email());
        userRepresentation.setEnabled(isActive);
        userRepresentation.setCredentials(List.of(credential));
        return userRepresentation;
    }

    private String extractUsername(String email) {
        return email.split("@")[0];
    }


    /**
     * Retrieves the current access token for the Keycloak client.
     * <p>
     * This method uses Keycloak's token manager to get the access token required
     * for making requests to protected endpoints.
     * </p>
     *
     * @return The access token.
     * @throws RuntimeException If there is an error while retrieving the access token.
     */
    public String getAccessToken() {
        try {
            var tokenResponse = keycloak.tokenManager().getAccessToken();
            return tokenResponse.getToken();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve access token", e);
        }
    }
}
