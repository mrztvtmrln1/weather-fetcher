-- changeset temirlan:20260312-1-change-user-status-to-varchar
ALTER TABLE users
ALTER COLUMN status TYPE VARCHAR(20);

-- Если нужно изменить дефолтное значение с TRUE на строку
ALTER TABLE users
    ALTER COLUMN status SET DEFAULT 'ACTIVE';