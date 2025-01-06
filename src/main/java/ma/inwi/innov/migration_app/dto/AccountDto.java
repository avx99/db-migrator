package ma.inwi.innov.migration_app.dto;

import lombok.Builder;

@Builder
public  record AccountDto(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
