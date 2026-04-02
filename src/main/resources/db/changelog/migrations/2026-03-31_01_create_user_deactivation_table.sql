CREATE TABLE merchant_deactivation_history (
                                               id BIGSERIAL PRIMARY KEY,
                                               user_id BIGINT NOT NULL,
                                               deactivation_reason VARCHAR(255) NOT NULL,
                                               deactivation_date TIMESTAMP,
                                               end_date TIMESTAMP

);