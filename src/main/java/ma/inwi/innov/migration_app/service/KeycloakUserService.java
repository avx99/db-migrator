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

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserService {

    private final KeycloakProperties keycloakProperties;

    @Lazy
    private final Keycloak keycloak;

    public String createUser(AccountDto account, Boolean isActive) {
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
    }


    private String extractUserId(Response response) {
        var location = response.getLocation().getPath();
        return location.substring(location.lastIndexOf('/') + 1);
    }


    private UserRepresentation mapToUserRepresentation(AccountDto account, Boolean isActive) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setSecretData(account.password());
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setCredentialData("{\"algorithm\":\"bcrypt\",\"hashIterations\":10}");
//        credential.setHashedSaltedValue(account.password());
//        credential.setHashIterations(4);

        var userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(account.firstName());
        userRepresentation.setLastName(account.lastName());
        userRepresentation.setEmail(account.email());
        userRepresentation.setUsername(extractUsername(account.email()));
        userRepresentation.setEnabled(isActive);
        userRepresentation.setCredentials(List.of(credential));
        return userRepresentation;
    }

    private String extractUsername(String email) {
        return email.split("@")[0];
    }

    private void setPasswordForUser(String userId, String password) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        keycloak.realm(keycloakProperties.getRealm())
                .users()
                .get(userId)
                .resetPassword(credential);
    }

}
