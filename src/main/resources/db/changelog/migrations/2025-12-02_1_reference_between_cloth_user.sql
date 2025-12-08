-- liquibase formatted sql

-- 1) добавляем колонку
-- changeset temirlan:20251202-1-add-user-id-column
ALTER TABLE clothes
    ADD COLUMN user_id INTEGER;

-- 2) создаём дефолтного юзера (если его ещё нет)
-- changeset temirlan:20251202-2-create-default-user
INSERT INTO users (first_name, last_name, login, profile_id)
VALUES ('Default', 'User', 'default_user', 1);

-- 3) заполняем user_id у всех вещей этим юзером
-- changeset temirlan:20251202-3-fill-user-id
UPDATE clothes
SET user_id = (SELECT user_id FROM users WHERE login = 'default_user')
WHERE user_id IS NULL;

-- 4) делаем NOT NULL + FK
-- changeset temirlan:20251202-4-set-not-null-and-fk
ALTER TABLE clothes
    ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE clothes
    ADD CONSTRAINT fk_clothes_user
        FOREIGN KEY (user_id) REFERENCES users(user_id);
