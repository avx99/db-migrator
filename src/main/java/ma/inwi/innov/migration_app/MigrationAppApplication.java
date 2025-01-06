package ma.inwi.innov.migration_app;

import ma.inwi.innov.migration_app.batch.ExecutorImpl;
import ma.inwi.innov.migration_app.jobs.spec.Job;
import ma.inwi.innov.migration_app.utils.ReflectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class MigrationAppApplication {
    private static final Integer BATCH_SIZE = 5;


    public static void main(String[] args)  {
        var ctx = SpringApplication.run(MigrationAppApplication.class, args);
        jobLauncher(ctx);
    }

    private static void jobLauncher(ApplicationContext ctx) {
        var jobs = ReflectionUtils.findExecutableClasses("ma.inwi.innov.migration_app.jobs");
        if (jobs != null) {
            for (var job : jobs) {
                var jobBean = (Job) ctx.getBean(job);
                var executor = new ExecutorImpl(jobBean);
                executor.execute(BATCH_SIZE);
            }
        }
    }

}
