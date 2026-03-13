CREATE TABLE user_block_job_execution (
        id SERIAL PRIMARY KEY,
        coverage_up_to TIMESTAMP NOT NULL,
        users INTEGER NOT NULL DEFAULT 0,
        created_at TIMESTAMP NOT NULL DEFAULT NOW(),
        completed_at TIMESTAMP
);