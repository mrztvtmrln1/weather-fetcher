INSERT INTO user_block_job_execution (
    id,
    coverage_up_to,
    users,
    created_at,
    completed_at
)
VALUES (
           1,
           NOW(),
           0,
           NOW(),
           NULL
       );