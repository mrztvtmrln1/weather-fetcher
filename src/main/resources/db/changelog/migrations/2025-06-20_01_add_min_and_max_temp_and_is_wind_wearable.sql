-- changeset temirlan:add_min_and_max_temp_and_is_wind_wearable
ALTER TABLE clothes
    ADD COLUMN min_temp INT,
    ADD COLUMN max_temp INT,
    ADD COLUMN is_wearable_in_wind BOOLEAN;
