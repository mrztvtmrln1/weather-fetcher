-- changeset temirlan:alter_clothes_add_wear_type
ALTER TABLE clothes
    ADD COLUMN wear_type VARCHAR(50);
