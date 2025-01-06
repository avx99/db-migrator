package ma.inwi.innov.migration_app.service;

import org.keycloak.representations.idm.CredentialRepresentation;

public class Credentials extends CredentialRepresentation {
    public void setHashedSaltedValue(String hashedSaltedValue) {
        super.hashedSaltedValue = hashedSaltedValue;
    }

    public void setHashIterations(Integer hashIterations) {
        super.hashIterations = hashIterations;
    }


}
