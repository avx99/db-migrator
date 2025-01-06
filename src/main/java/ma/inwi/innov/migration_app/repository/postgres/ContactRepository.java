package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Contact;
import ma.inwi.innov.migration_app.enumeration.ContactSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    long countByMailAndContactSource(String mail, ContactSource contactSource);

}