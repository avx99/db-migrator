package ma.inwi.innov.migration_app.batch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.batch.spec.Executor;
import ma.inwi.innov.migration_app.jobs.spec.Job;

@Slf4j
@AllArgsConstructor
public class ExecutorImpl implements Executor {
    private Job job;

    @Override
    public void execute(Integer batchSize) {
        var count = job.getSize();
        var steps = count / batchSize;
        var left = count % batchSize;
        for (var i = 0; i < steps; i++) {
            job.migrate(i, batchSize);
            if(i == steps - 1) {
                job.migrate(i + 1, (int) (left));
            }
        }
    }
}
