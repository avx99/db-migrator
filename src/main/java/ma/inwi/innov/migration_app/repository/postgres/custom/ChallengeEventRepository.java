package ma.inwi.innov.migration_app.repository.postgres.custom;

import ma.inwi.innov.migration_app.domain.postgres.custom.ChallengeEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ChallengeEventRepository extends JpaRepository<ChallengeEvent, UUID> {

    @Query(value = """
            select
               gen_random_uuid() as id,
               null as event_id,
               c.id as challenge_id,
               c.title AS title,
               c.description,
               c.date,
               c.picture_url AS image,
               c.status,
               c.pinned,
               'CHALLENGE' AS type
            from 
                innov.challenge c
            where (:date is null or c.date = cast(:date as date))
              and (:status is null or c.status = :status)
              and (:type is null or 'CHALLENGE' = :type)
            
            union all
            
            select
                gen_random_uuid() as id,
                e.id as event_id,
                null as challenge_id,
                e.event_title AS title,
                e.description,
                e.date,
                e.picture_url AS image,
                e.status,
                e.pinned,
                'EVENT' AS type
            from 
                innov.event e
            where (:date is null or e.date = cast(:date as date))
              and (:status is null or e.status = :status)
              and (:type is null or 'EVENT' = :type)
            """,
            countQuery = """
                    select count(*) from (
                        select c.id
                        from innov.challenge c
                        where (:date is null or c.date = cast(:date as date))
                          and (:status is null or c.status = :status)
                          and (:type is null or 'CHALLENGE' = :type)
                    
                        union all
                    
                        select e.id
                        from innov.event e
                        where (:date is null or e.date = cast(:date as date))
                          and (:status is null or e.status = :status)
                          and (:type is null or 'EVENT' = :type)
                    ) as combined
                    """,
            nativeQuery = true)
    Page<ChallengeEvent> searchChallengeAndEvents(
            @Param("date") String date,
            @Param("status") String status,
            @Param("type") String type,
            Pageable pageable
    );
}

