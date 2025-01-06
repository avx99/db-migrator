package ma.inwi.innov.migration_app.jobs.spec;

public interface Job {
    void migrate(int page, int size);
    Long getSize();
}
