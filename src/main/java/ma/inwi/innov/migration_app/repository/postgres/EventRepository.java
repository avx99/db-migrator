package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByOrderByCreatedAtDescUpdatedAtDesc();

    @Modifying
    @Query(value = "DELETE FROM innov.event_media em WHERE em.event_id IN (select id from innov.event  where version  = '1.0.0')", nativeQuery = true)
    void deleteEventMediaByUserVersion();

    @Modifying
    @Query(value = "DELETE FROM innov.events_users eu WHERE eu.event_id IN (select id from innov.event  where version  = '1.0.0')", nativeQuery = true)
    void deleteEventsUsersByUserVersion();

    @Modifying
    @Query(value = "DELETE FROM innov.partner p WHERE p.event_id IN (select id from innov.event  where version  = '1.0.0')", nativeQuery = true)
    void deletePartnerByUserVersion();

    @Modifying
    @Query(value = "DELETE FROM innov.planning p WHERE p.event_id IN (select id from innov.event  where version  = '1.0.0')", nativeQuery = true)
    void deletePlanningByUserVersion();

    @Modifying
    @Query(value = "DELETE FROM innov.speaker s WHERE s.event_id IN (select id from innov.event  where version  = '1.0.0')", nativeQuery = true)
    void deleteSpeakerByUserVersion();


    @Modifying
    @Query(value = "delete from innov.event  where version  = '1.0.0'\n", nativeQuery = true)
    void deleteEvents();
}
