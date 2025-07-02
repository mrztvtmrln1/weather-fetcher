-- changeset temirlan:add_color_to_cloth
ALTER TABLE clothes
    ADD COLUMN cloth_color VARCHAR(100);