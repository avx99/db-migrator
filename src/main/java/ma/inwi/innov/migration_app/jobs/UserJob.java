package ma.inwi.innov.migration_app.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.annotations.Executable;
import ma.inwi.innov.migration_app.domain.postgres.User;
import ma.inwi.innov.migration_app.dto.AccountDto;
import ma.inwi.innov.migration_app.jobs.spec.Job;
import ma.inwi.innov.migration_app.repository.postgres.UserRepository;
import ma.inwi.innov.migration_app.service.KeycloakUserService;
import ma.inwi.innov.migration_app.utils.DateUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;

@Executable
@Component
@Slf4j
@RequiredArgsConstructor
public class UserJob implements Job {
    private final UserRepository userRepository;
    @PersistenceContext(unitName = "mysql")
    private EntityManager mysqlEntityManager;
    @Value("${migration.utils.static-files-root}")
    private String staticFilesRoot;
    private final KeycloakUserService keycloakUserService;

    @Override
    @Transactional(transactionManager = "mysqlTransactionManager", readOnly = true)
    public void migrate(int page, int size) {
        var offset = page * size;
        var sql = String.format("SELECT * FROM users where id  = 284 LIMIT %d OFFSET %d", size, offset);
        var query = mysqlEntityManager.createNativeQuery(sql);
        var results = query.getResultList();
        for (var record : results) {
            var userBuilder = User.builder();
            var recordRows = (Object[]) record;
            var keycloakId = keycloakUserService.createUser(mapUser(recordRows), recordRows[23] != null && ((String) recordRows[23]).trim().equals("Activé"));
            mapUser(recordRows, userBuilder, keycloakId);
            var user = userBuilder.build();
        }
    }

    @Override
    public Long getSize() {
        var sql = "SELECT count(*) FROM users";
        var query = mysqlEntityManager.createNativeQuery(sql);
        var results = query.getResultList();
        return (results != null && !results.isEmpty()) ? (Long) results.get(0) : 0L;
    }

    private void mapUser(Object[] record, User.UserBuilder<?, ?> userBuilder, String keycloakId) {
        userBuilder.keycloakId(keycloakId);
        userBuilder.birthDate(DateUtils.convertToLocalDate((Date) record[7], false));
        userBuilder.phoneNumber((String) record[11]);
        userBuilder.firstName((String) record[25]);
        userBuilder.lastName((String) record[26]);
        userBuilder.city((String) record[10]);
        userBuilder.job((String) record[13]);
        userBuilder.email((String) record[2]);
        userBuilder.username(record[2] != null ? ((String) record[2]).split("@")[0] : null);
        userBuilder.image(record[8] != null ? staticFilesRoot + record[8] : null);
        //TODO : see if its same thing as 'job'
        userBuilder.profession((String) record[13]);
        userBuilder.inwiPhone((String) record[12]);
        userBuilder.active(record[23] != null && ((String) record[23]).trim() != null && (((String) record[23]).trim()).equals("Activé"));
        userBuilder.company((String) record[12]);
        userBuilder.educationLevel((String) record[28]);
        userBuilder.skills((String) record[16]);
        userBuilder.yearsOfExperience(NumberUtils.isCreatable((String) record[16]) ? (Integer) record[16] : null);
        userBuilder.createdAt(DateUtils.convertToLocalDateTime((Timestamp) record[5], true));
        userBuilder.updatedAt(DateUtils.convertToLocalDateTime((Timestamp) record[6], false));
    }

    private AccountDto mapUser(Object[] record) {
        return AccountDto
                .builder()
                .email((String) record[2])
                .firstName((String) record[25])
                .lastName((String) record[26])
                .password((String) record[3])
                .build();
    }
}
