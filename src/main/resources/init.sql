CREATE TABLE migration (
    id SERIAL PRIMARY KEY,
    version VARCHAR(255) NOT NULL
);

CREATE TABLE job (
    id SERIAL PRIMARY KEY,
    version VARCHAR(255) NOT NULL,
    active BOOLEAN,
    executed BOOLEAN,
    roll_backed BOOLEAN,
    order_number INTEGER,
    name VARCHAR(255),
    description TEXT,
    migration_id INTEGER,
    FOREIGN KEY (migration_id) REFERENCES migration (id)
);

CREATE TABLE migration_operation (
    id SERIAL PRIMARY KEY,
    execution_date TIMESTAMP,
    job_id INTEGER,
    FOREIGN KEY (job_id) REFERENCES job (id)
);

CREATE TABLE rollback_operation (
    id SERIAL PRIMARY KEY,
    execution_date TIMESTAMP,
    job_id INTEGER,
    FOREIGN KEY (job_id) REFERENCES job (id)
);

CREATE TABLE event (
    id SERIAL PRIMARY KEY,
    event_type VARCHAR(255) NOT NULL,
    message TEXT,
    date_of_reception TIMESTAMP,
    migration_operation_id INTEGER,
    rollback_operation_id INTEGER,
    FOREIGN KEY (migration_operation_id) REFERENCES migration_operation (id),
    FOREIGN KEY (rollback_operation_id) REFERENCES rollback_operation (id)
);
