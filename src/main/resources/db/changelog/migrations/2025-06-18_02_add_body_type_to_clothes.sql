-- changeset temirlan:add_body_type_to_clothes
ALTER TABLE clothes
    ADD COLUMN body_type VARCHAR(100);
