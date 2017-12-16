--liquibase formatted sql
--changeset salerno:3

INSERT INTO user (ID, CREATED_ON, UPDATED_ON, USERNAME, ALIAS, PASSWORD_HASH, PERSON_ID) VALUES ('136dacaf-a953-44b3-90e2-e3f0f4939981', date("1970-01-01 00:00:00"), null, 'system', 'system', 'bbc5e661e106c6dcd8dc6dd186454c2fcba3c710fb4d8e71a60c93eaf077f073', null);
INSERT INTO user (ID, CREATED_ON, UPDATED_ON, USERNAME, ALIAS, PASSWORD_HASH, PERSON_ID) VALUES ('136dacaf-a953-44b3-90e2-e3f0f4939982', date("1970-01-01 00:00:00"), null, 'guest', 'guest', 'bbc5e661e106c6dcd8dc6dd186454c2fcba3c710fb4d8e71a60c93eaf077f072', null);
INSERT INTO password_requirement (ID, CREATED_ON, UPDATED_ON, TITLE, ENABLED, WHITELIST, BLACKLIST, COUNT_MIN, COUNT_MAX, MESSAGE) VALUES ('136dacaf-a953-44b3-90e2-e3f0f4939981', date("1970-01-01 00:00:00"), null, 'default', 1, null, null, 4, -1, 'Password is too short');

-- rollback delete from user where id = '136dacaf-a953-44b3-90e2-e3f0f4939981';
-- rollback delete from user where id = '136dacaf-a953-44b3-90e2-e3f0f4939982';
-- rollback delete from password_requirement where id = '136dacaf-a953-44b3-90e2-e3f0f4939981';