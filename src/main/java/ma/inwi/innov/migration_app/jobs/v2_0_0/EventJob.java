package ma.inwi.innov.migration_app.jobs.v2_0_0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.annotations.Executable;
import ma.inwi.innov.migration_app.domain.postgres.Event;
import ma.inwi.innov.migration_app.jobs.spec.Job;
import org.springframework.stereotype.Component;

@Executable(version = "2.0.0", order = "2")
@Component("EventJobV2.0.0")
@Slf4j
@RequiredArgsConstructor
public class EventJob implements Job<Event> {
    @Override
    public void migrate(int page, int size, String version) {
        log.info("########### migrate EventJobV2.0.0");
    }

    @Override
    public Long getSize() {
        log.info("########### getSize EventJobV2.0.0");
        return 0L;
    }

    @Override
    public void rollback(String version) {
        log.info("########### rollback EventJobV2.0.0");

    }
}
