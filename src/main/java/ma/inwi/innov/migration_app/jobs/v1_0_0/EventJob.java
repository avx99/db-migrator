package ma.inwi.innov.migration_app.jobs.v1_0_0;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.annotations.Executable;
import ma.inwi.innov.migration_app.domain.postgres.*;
import ma.inwi.innov.migration_app.jobs.spec.Job;
import ma.inwi.innov.migration_app.repository.postgres.EventRepository;
import ma.inwi.innov.migration_app.utils.ConversionUtils;
import ma.inwi.innov.migration_app.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

@Executable(version = "1.0.0", order = "2")
@Component
@Slf4j
@RequiredArgsConstructor
public class EventJob implements Job<Event> {

    private final EventRepository eventRepository;
    @PersistenceContext(unitName = "mysql")
    private EntityManager mysqlEntityManager;
    @Value("${migration.utils.static-files-root}")
    private String staticFilesRoot;

    @Transactional(transactionManager = "mysqlTransactionManager", readOnly = true)
    public void migrate(int page, int size, String version) {
        var offset = page * size;
        var eventBuilder = Event.builder();
        var sql = String.format("SELECT * FROM events LIMIT %d OFFSET %d", size, offset);
        var query = mysqlEntityManager.createNativeQuery(sql);
        var results = query.getResultList();
        for (var record : results) {
            var recordRows = (Object[]) record;
            mapEventsDetails(recordRows, eventBuilder);

            //TODO check the utility of planning2
            //plannings2
            var eventPlanning2Sql = String.format("select * from `event_planing2` where `event_planing2`.`event_id` in (%d)", (Integer) recordRows[0]);
            var eventPlanning2Query = mysqlEntityManager.createNativeQuery(eventPlanning2Sql);
            var eventPlanning2Results = eventPlanning2Query.getResultList();

            for (var eventPlanning2Record : eventPlanning2Results) {
                var eventPlanning2RecordRows = (Object[]) eventPlanning2Record;

            }


            //plannings
            var eventPlanningSql = String.format("""
                    SELECT
                        pt.*,
                        ep.day
                    FROM
                        planing_timeslot pt
                    JOIN
                        event_planing ep ON pt.event_planing_id = ep.id
                    JOIN
                        event_city ec ON ep.event_city_id = ec.id
                    WHERE
                        ec.event_id = %d;
                    """, (Integer) recordRows[0]);
            var eventPlanningQuery = mysqlEntityManager.createNativeQuery(eventPlanningSql);
            var eventPlanningResults = eventPlanningQuery.getResultList();
            var plannings = new ArrayList<Planning>();
            for (var eventPlanningRecord : eventPlanningResults) {
                var eventPlanningRecordRows = (Object[]) eventPlanningRecord;
                plannings.add(mapEventsPlanning(eventPlanningRecordRows));
            }
            eventBuilder.plannings(plannings);


            //categories
            var eventCategoriesSql = String.format("""
                    select
                    	`categories` .*,
                    	`event_category`.`event_id` as `pivot_event_id`,
                    	`event_category`.`category_id` as `pivot_category_id`
                    from
                    	`categories`
                    inner join `event_category` on
                    	`categories`.`id` = `event_category`.`category_id`
                    where
                    	`event_category`.`event_id` in (%d);
                    """, (Integer) recordRows[0]);
            var eventCategoriesQuery = mysqlEntityManager.createNativeQuery(eventCategoriesSql);
            var eventCategoriesResults = eventCategoriesQuery.getResultList();
            var categories = new ArrayList<Category>();
            for (var eventCategoriesRecord : eventCategoriesResults) {
                var eventCategoriesRecordRows = (Object[]) eventCategoriesRecord;
                categories.add(mapEventsCategory(eventCategoriesRecordRows));
            }
            eventBuilder.categories(categories);

            //city
            var eventCitySql = String.format("""
                    select * from `cities` where `cities`.`id` = (select city_id from event_city where event_id = %d limit 1) limit 1;
                    """, (Integer) recordRows[0]);
            var eventCityQuery = mysqlEntityManager.createNativeQuery(eventCitySql);
            var eventCityResults = eventCityQuery.getResultList();
            if (eventCityResults != null && !eventCityResults.isEmpty()) {
                var eventCityRecordRows = (Object[]) eventCityResults.get(0);
                mapEventLocation(eventCityRecordRows, eventBuilder);
            }


            //media
            var eventMediaSql = String.format("""
                    select
                    	*
                    from
                    	`event_media`
                    where
                    	`event_media`.`event_id` = %d;
                    """, (Integer) recordRows[0]);
            var eventMediaQuery = mysqlEntityManager.createNativeQuery(eventMediaSql);
            var eventMediaResults = eventMediaQuery.getResultList();
            var mediaLists = new ArrayList<EventMedia>();
            for (var eventMediaRecord : eventMediaResults) {
                var eventMediaRecordRows = (Object[]) eventMediaRecord;
                var media = mapEventMedias(eventMediaRecordRows);
                mediaLists.add(media);
            }
            eventBuilder.eventMedias(mediaLists);

            //contributors
            var eventContributorsSql = String.format("""
                    select
                    	c.*
                    from
                    	contributors c
                    inner join event_contributor ec on
                    	c.id = ec.contributor_id\s
                    where
                    	ec.event_id = %d;
                    """, (Integer) recordRows[0]);
            var eventContributorsQuery = mysqlEntityManager.createNativeQuery(eventContributorsSql);
            var eventContributorsResults = eventContributorsQuery.getResultList();
            var speakers = new ArrayList<Speaker>();
            for (var eventContributorsRecord : eventContributorsResults) {
                var eventContributorsRecordRows = (Object[]) eventContributorsRecord;
                var speaker = mapEventSpeaker(eventContributorsRecordRows);
                speakers.add(speaker);
            }
            eventBuilder.speakers(speakers);

            //partners
            var eventPartnersSql = String.format("""
                    select
                    	p.*
                    from
                    	partners p
                    inner join event_partner ep on
                    	p.id = ep.partner_id
                    where
                    	ep.event_id = %d;
                    """, (Integer) recordRows[0]);
            var eventPartnersQuery = mysqlEntityManager.createNativeQuery(eventPartnersSql);
            var eventPartnersResults = eventPartnersQuery.getResultList();
            var partners = new ArrayList<Partner>();
            for (var eventPartnersRecord : eventPartnersResults) {
                var eventPartnersRecordRows = (Object[]) eventPartnersRecord;
                var partner = mapEventPartners(eventPartnersRecordRows);
                partners.add(partner);
            }

            //users
            var eventUsersSql = String.format("""
                    select
                    	*
                    from
                    	event_participant
                    where
                    	event_id = %d;
                    """, (Integer) recordRows[0]);
            var eventUsersQuery = mysqlEntityManager.createNativeQuery(eventPartnersSql);
            var eventUsersResults = eventPartnersQuery.getResultList();
            var users = new ArrayList<User>();
            for (var eventUsersRecord : eventUsersResults) {
                var eventUsersRecordRows = (Object[]) eventUsersRecord;
                var user = mapEventUsers(eventUsersRecordRows);
                users.add(user);
            }

            eventBuilder.partners(partners);
            eventBuilder.version(version);
            var event = eventBuilder.build();
            fixReferences(event);
            eventRepository.save(event);
        }

    }

    public Long getSize() {
        var sql = "SELECT count(*) FROM events";
        var query = mysqlEntityManager.createNativeQuery(sql);
        var results = query.getResultList();
        return (results != null && !results.isEmpty()) ? (Long) results.getFirst() : 0L;
    }

    private void mapEventsDetails(Object[] record, Event.EventBuilder<?, ?> eventBuilder) {
        eventBuilder.migrated(true);
        eventBuilder.eventTitle((String) record[1]);
        eventBuilder.description((String) record[2]);
        eventBuilder.pictureUrl(staticFilesRoot + (String) record[3]);
        eventBuilder.createdAt(DateUtils.convertToLocalDateTime((Timestamp) record[4], true));
        eventBuilder.updatedAt(DateUtils.convertToLocalDateTime((Timestamp) record[5], false));
        //TODO : make sure its eq of active:
        eventBuilder.publish(ConversionUtils.convertToBoolean(((Boolean) record[7]).toString()));
        //TODO : make sure cta_inscription(9) is useless
        //TODO : use url_inscription_externe(10) in medias
        //TODO : make sure without_city(11) is useless
        eventBuilder.startInscriptionDate(DateUtils.convertToLocalDateTime((Timestamp) record[12], false));
        eventBuilder.endInscriptionDate(DateUtils.convertToLocalDateTime((Timestamp) record[13], false));
        eventBuilder.isLive(ConversionUtils.convertToBoolean(((Boolean) record[14]).toString()));
        //TODO : make sure places_live(15) is useless
        //TODO : make sure compan_on(16) is useless
        //TODO : make sure live_label(17) is useless
    }


    private Planning mapEventsPlanning(Object[] record) {
        var planningBuilder = Planning.builder();
        var startTime = DateUtils.convertToLocalTime((String) record[1], null, false);
        var endTime = DateUtils.convertToLocalTime((String) record[2], null, false);
//        var duration = (startTime != null && endTime != null) ? ChronoUnit.MINUTES.between(startTime, endTime) : 0;
        planningBuilder.timeStart(startTime);
        planningBuilder.timeEnd(endTime);
        planningBuilder.activity((String) record[3]);
        planningBuilder.date(DateUtils.convertToLocalDate((String) record[7], false));
        return planningBuilder.build();
    }

    private Category mapEventsCategory(Object[] record) {
        var categoryBuilder = Category.builder();
        categoryBuilder.name((String) record[1]);
        categoryBuilder.typeCategory((String) record[2]);
        return categoryBuilder.build();
    }

    private void mapEventLocation(Object[] record, Event.EventBuilder<?, ?> eventBuilder) {
        eventBuilder.city((String) record[1]);
    }


    private EventMedia mapEventMedias(Object[] record) {
        var eventMediaBuilder = EventMedia.builder();
        eventMediaBuilder.type(mapEventType((String) record[1]));
        eventMediaBuilder.url(staticFilesRoot + record[2]);
        eventMediaBuilder.createdAt(DateUtils.convertToLocalDateTime((Timestamp) record[5], false));
        eventMediaBuilder.updatedAt(DateUtils.convertToLocalDateTime((Timestamp) record[6], false));
        return eventMediaBuilder.build();
    }

    private Partner mapEventPartners(Object[] record) {
        var eventPartnerBuilder = Partner.builder();
        eventPartnerBuilder.imageUrl(staticFilesRoot + record[2]);
        return eventPartnerBuilder.build();
    }

    private Speaker mapEventSpeaker(Object[] record) {
        var eventSpeakerBuilder = Speaker.builder();
        eventSpeakerBuilder.imageUrl(staticFilesRoot + record[4]);
        return eventSpeakerBuilder.build();
    }


    private String mapEventType(String source) {
        return switch (source) {
            case "image" -> "Image";
            case "video" -> "Video";
            case "youtube" -> "youtube";
            case "application" -> "application";
            default -> null;
        };
    }

    private User mapEventUsers(Object[] records) {
        return User.builder().id((Integer) records[2]).build();
    }

    private void fixReferences(Event event) {
        if (event.getEventMedias() != null) {
            event.getEventMedias().forEach(eventMedia -> eventMedia.setEvent(event));
        }
        if (event.getEventArticles() != null) {
            event.getEventArticles().forEach(articleEvent -> articleEvent.setEvent(event));
        }
        if (event.getPlannings() != null) {
            event.getPlannings().forEach(planning -> planning.setEvent(event));
        }
        if (event.getWinners() != null) {
            event.getWinners().forEach(eventWinner -> eventWinner.setEvent(event));
        }
        if (event.getSpeakers() != null) {
            event.getSpeakers().forEach(speaker -> speaker.setEvent(event));
        }
        if (event.getCategories() != null) {
            event.getCategories().forEach(category -> category.setEvent(event));
        }
        if (event.getPartners() != null) {
            event.getPartners().forEach(partner -> partner.setEvent(event));
        }
    }

}
