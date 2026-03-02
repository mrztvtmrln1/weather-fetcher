-- 1) добавляем колонку
-- changeset temirlan:20260302-1-add-user-status-column
ALTER TABLE users
    ADD COLUMN status BOOLEAN DEFAULT TRUE NOT NULL;