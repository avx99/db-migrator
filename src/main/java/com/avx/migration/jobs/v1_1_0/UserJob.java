package com.avx.migration.jobs.v1_1_0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.avx.migration.annotations.Executable;
import com.avx.migration.domain.User;
import com.avx.migration.jobs.spec.Job;
import org.springframework.stereotype.Component;

@Executable(version = "1.1.0", order = "1")
@Component("UserJobV1.1.0")
@Slf4j
@RequiredArgsConstructor
public class UserJob implements Job<User> {
    @Override
    public void migrate(int page, int size, String version) {
        log.info("########### migrate UserJobV1.1.0");
    }

    @Override
    public Long getSize() {
        log.info("########### getSize UserJobV1.1.0");
        return 3L;
    }

    @Override
    public void rollback(String version) {
        log.info("########### rollback UserJobV1.1.0");
    }
}
