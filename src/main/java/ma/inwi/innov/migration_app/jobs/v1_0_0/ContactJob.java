package ma.inwi.innov.migration_app.jobs.v1_0_0;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.annotations.Executable;
import ma.inwi.innov.migration_app.domain.postgres.Contact;
import ma.inwi.innov.migration_app.enumeration.ContactSource;
import ma.inwi.innov.migration_app.enumeration.mail.SenderType;
import ma.inwi.innov.migration_app.jobs.spec.Job;
import ma.inwi.innov.migration_app.repository.postgres.ContactRepository;
import ma.inwi.innov.migration_app.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Executable(version = "1.0.0", order = "3")
@Component
@Slf4j
@RequiredArgsConstructor
public class ContactJob implements Job<Contact> {
    private final ContactRepository contactRepository;
    @PersistenceContext(unitName = "mysql")
    private EntityManager mysqlEntityManager;
    @Value("${migration.utils.static-files-root}")
    private String staticFilesRoot;

    @Transactional(transactionManager = "mysqlTransactionManager", readOnly = true)
    public void migrate(int page, int size, String version) {
        var offset = page * size;
        var contactBuilder = Contact.builder();
        var sql = String.format("SELECT * FROM contacts LIMIT %d OFFSET %d", size, offset);
        var query = mysqlEntityManager.createNativeQuery(sql);
        var results = query.getResultList();
        for (var record : results) {
            var recordRows = (Object[]) record;
            mapContacts(recordRows, contactBuilder);
            contactBuilder.version(version);
            contactRepository.save(contactBuilder.build());
        }
    }

    private void mapContacts(Object[] record, Contact.ContactBuilder<?, ?> contactBuilder) {
        contactBuilder.firstName((String) record[1]);
        contactBuilder.lastName((String) record[2]);
        contactBuilder.mail((String) record[3]);
        contactBuilder.subject((String) record[4]);
        contactBuilder.senderType(getSenderType((Integer) record[5]));
        contactBuilder.message((String) record[6]);
        contactBuilder.createdAt(DateUtils.convertToLocalDateTime((Timestamp) record[7], true));
        contactBuilder.updatedAt(DateUtils.convertToLocalDateTime((Timestamp) record[8], false));
        contactBuilder.contactSource(ContactSource.CONTACT_US);
    }


    public static SenderType getSenderType(Integer value) {
        return switch (value) {
            case 1 -> SenderType.IDEA_HOLDER;
            case 2 -> SenderType.PROJECT_HOLDER;
            case 3 -> SenderType.CONFIRMED_ENTREPRENEUR;
            case 4 -> SenderType.INVESTOR;
            case 5 -> SenderType.INCUBATOR_ACCELERATOR;
            case 6 -> SenderType.STUDENT;
            case 7 -> SenderType.UNIVERSITY;
            default -> null;
        };
    }

    public Long getSize() {
        var sql = "SELECT count(*) FROM contacts";
        var query = mysqlEntityManager.createNativeQuery(sql);
        var results = query.getResultList();
        return (results != null && !results.isEmpty()) ? (Long) results.getFirst() : 0L;
    }
}
