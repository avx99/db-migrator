-- contact definition

-- Drop table

-- DROP TABLE contact;

CREATE TABLE contact (
	id serial4 NOT NULL,
	first_name varchar(255) NULL,
	mail varchar(255) NULL,
	subject varchar(255) NULL,
	message text NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	last_name varchar(255) NULL,
	sender_type varchar(255) NULL,
	company varchar(255) NULL,
	contact_source varchar(255) NULL,
	CONSTRAINT contact_pkey PRIMARY KEY (id)
);


-- "event" definition

-- Drop table

-- DROP TABLE "event";

CREATE TABLE "event" (
	id serial4 NOT NULL,
	"name" varchar(255) NULL,
	"date" date NULL,
	picture_url varchar(255) NULL,
	"location" varchar(255) NULL,
	participation bool NULL,
	event_title varchar(255) NULL,
	description text NULL,
	status varchar(255) NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	publish bool NULL,
	"time" time NULL,
	pinned bool NULL,
	CONSTRAINT event_pkey PRIMARY KEY (id)
);


-- flyway_schema_history definition

-- Drop table

-- DROP TABLE flyway_schema_history;

CREATE TABLE flyway_schema_history (
	installed_rank int4 NOT NULL,
	"version" varchar(50) NULL,
	description varchar(200) NOT NULL,
	"type" varchar(20) NOT NULL,
	script varchar(1000) NOT NULL,
	checksum int4 NULL,
	installed_by varchar(100) NOT NULL,
	installed_on timestamp DEFAULT now() NOT NULL,
	execution_time int4 NOT NULL,
	success bool NOT NULL,
	CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);
CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history USING btree (success);


-- innov_document definition

-- Drop table

-- DROP TABLE innov_document;

CREATE TABLE innov_document (
	id serial4 NOT NULL,
	document_id varchar(255) NOT NULL,
	"type" varchar(50) NOT NULL,
	entity_name varchar(255) NULL,
	"location" varchar(255) NULL,
	filename varchar(255) NULL,
	"size" int8 NULL,
	mime_type varchar(50) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	last_modified_by varchar(255) NULL,
	CONSTRAINT innov_document_pkey PRIMARY KEY (id),
	CONSTRAINT innov_document_type_check CHECK (((type)::text = ANY ((ARRAY['INTERNAL'::character varying, 'EXTERNAL'::character varying])::text[])))
);


-- "role" definition

-- Drop table

-- DROP TABLE "role";

CREATE TABLE "role" (
	id serial4 NOT NULL,
	code varchar(50) NULL,
	"label" varchar(255) NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);


-- article_event definition

-- Drop table

-- DROP TABLE article_event;

CREATE TABLE article_event (
	id serial4 NOT NULL,
	article_id varchar NOT NULL,
	event_id int4 NOT NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT article_event_pkey PRIMARY KEY (id),
	CONSTRAINT article_event_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- challenge definition

-- Drop table

-- DROP TABLE challenge;

CREATE TABLE challenge (
	id serial4 NOT NULL,
	"name" varchar(255) NULL,
	"date" date NULL,
	picture_url varchar(255) NULL,
	title varchar(255) NULL,
	description text NULL,
	regulation text NULL,
	status varchar(255) NULL,
	event_id int4 NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	pinned bool NULL,
	CONSTRAINT challenge_pkey PRIMARY KEY (id),
	CONSTRAINT challenge_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- event_media definition

-- Drop table

-- DROP TABLE event_media;

CREATE TABLE event_media (
	id serial4 NOT NULL,
	url varchar(255) NULL,
	"type" varchar(50) NULL,
	event_id int4 NOT NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT event_media_pkey PRIMARY KEY (id),
	CONSTRAINT event_media_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- event_winner definition

-- Drop table

-- DROP TABLE event_winner;

CREATE TABLE event_winner (
	id serial4 NOT NULL,
	ranking varchar(255) NOT NULL,
	company_name varchar(255) NOT NULL,
	description text NULL,
	url_image varchar(255) NULL,
	event_id int4 NOT NULL,
	CONSTRAINT event_winner_pkey PRIMARY KEY (id),
	CONSTRAINT event_winner_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- partner definition

-- Drop table

-- DROP TABLE partner;

CREATE TABLE partner (
	id serial4 NOT NULL,
	image_url varchar(255) NULL,
	event_id int4 NOT NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT partner_pkey PRIMARY KEY (id),
	CONSTRAINT partner_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- planning definition

-- Drop table

-- DROP TABLE planning;

CREATE TABLE planning (
	id serial4 NOT NULL,
	"date" date NULL,
	"time" time NULL,
	activity varchar(255) NULL,
	duration int4 NULL,
	event_id int4 NOT NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT planning_pkey PRIMARY KEY (id),
	CONSTRAINT planning_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- "section" definition

-- Drop table

-- DROP TABLE "section";

CREATE TABLE "section" (
	id serial4 NOT NULL,
	title varchar(255) NULL,
	description text NULL,
	challenge_id int4 NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT section_pkey PRIMARY KEY (id),
	CONSTRAINT section_challenge_id_fkey FOREIGN KEY (challenge_id) REFERENCES challenge(id)
);


-- speaker definition

-- Drop table

-- DROP TABLE speaker;

CREATE TABLE speaker (
	id serial4 NOT NULL,
	image_url varchar(255) NULL,
	event_id int4 NOT NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT speaker_pkey PRIMARY KEY (id),
	CONSTRAINT speaker_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id)
);


-- "user" definition

-- Drop table

-- DROP TABLE "user";

CREATE TABLE "user" (
	id serial4 NOT NULL,
	birth_date date NULL,
	phone_number varchar(255) NULL,
	profession varchar(255) NULL,
	inwi_phone varchar(255) NULL,
	company varchar(255) NULL,
	education_level varchar(255) NULL,
	skills text NULL,
	years_of_experience int4 NULL,
	role_id int4 NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	city varchar(255) NULL,
	job varchar(255) NULL,
	email varchar(255) NULL,
	username varchar(255) NULL,
	image varchar(255) NULL,
	keycloak_id varchar(255) NULL,
	gender varchar(255) NULL,
	is_deleted bool NULL,
	CONSTRAINT unique_username UNIQUE (username),
	CONSTRAINT user_pkey PRIMARY KEY (id),
	CONSTRAINT user_role_id_fkey FOREIGN KEY (role_id) REFERENCES "role"(id)
);


-- events_users definition

-- Drop table

-- DROP TABLE events_users;

CREATE TABLE events_users (
	id serial4 NOT NULL,
	event_id serial4 NOT NULL,
	user_id serial4 NOT NULL,
	CONSTRAINT events_users_pkey PRIMARY KEY (id),
	CONSTRAINT unq_event_user UNIQUE (event_id, user_id),
	CONSTRAINT events_users_event_id_fkey FOREIGN KEY (event_id) REFERENCES "event"(id),
	CONSTRAINT events_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id)
);


-- project definition

-- Drop table

-- DROP TABLE project;

CREATE TABLE project (
	id serial4 NOT NULL,
	startup_name varchar(255) NULL,
	city varchar(255) NULL,
	country varchar(255) NULL,
	address varchar(255) NULL,
	number_of_employees int4 NULL,
	linkedin_url varchar(255) NULL,
	website_url varchar(255) NULL,
	"date" date NULL,
	business_field varchar(255) NULL,
	problem text NULL,
	description text NULL,
	file_url varchar(255) NULL,
	video_url varchar(255) NULL,
	status varchar(50) NULL,
	regulation text NULL,
	maturity_stage varchar(50) NULL,
	jury_id int4 NULL,
	depositor_id int4 NULL,
	challenge_id int4 NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	pinned bool NULL,
	title varchar(255) NULL,
	image_url varchar(255) NULL,
	CONSTRAINT project_pkey PRIMARY KEY (id),
	CONSTRAINT project_challenge_id_fkey FOREIGN KEY (challenge_id) REFERENCES challenge(id),
	CONSTRAINT project_depositor_id_fkey FOREIGN KEY (depositor_id) REFERENCES "user"(id),
	CONSTRAINT project_jury_id_fkey FOREIGN KEY (jury_id) REFERENCES "user"(id)
);


-- winner definition

-- Drop table

-- DROP TABLE winner;

CREATE TABLE winner (
	id serial4 NOT NULL,
	ranking int4 NULL,
	challenge_id int4 NOT NULL,
	project_id int4 NOT NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT winner_pkey PRIMARY KEY (id),
	CONSTRAINT winner_challenge_id_fkey FOREIGN KEY (challenge_id) REFERENCES challenge(id),
	CONSTRAINT winner_project_id_fkey FOREIGN KEY (project_id) REFERENCES project(id)
);


-- evaluation definition

-- Drop table

-- DROP TABLE evaluation;

CREATE TABLE evaluation (
	id serial4 NOT NULL,
	score int4 NULL,
	"comment" text NULL,
	project_id int4 NULL,
	section_id int4 NULL,
	last_modified_by varchar(255) NULL,
	created_by varchar(255) NULL,
	deleted_at timestamp NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT evaluation_pkey PRIMARY KEY (id),
	CONSTRAINT evaluation_project_id_fkey FOREIGN KEY (project_id) REFERENCES project(id),
	CONSTRAINT evaluation_section_id_fkey FOREIGN KEY (section_id) REFERENCES "section"(id)
);