package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Contact;
import ma.inwi.innov.migration_app.enumeration.ContactSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    long countByMailAndContactSource(String mail, ContactSource contactSource);

    @Modifying
    @Query(value = "delete from innov.contact c where c.version = '1.0.0'", nativeQuery = true)
    void deleteContactsByVersion();

}