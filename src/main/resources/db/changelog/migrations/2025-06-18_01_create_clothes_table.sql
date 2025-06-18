-- changeset temirlan:create_clothes_table
CREATE TABLE clothes (
    id SERIAL PRIMARY KEY,
    cloth_name VARCHAR(255) NOT NULL,
    season_type VARCHAR(50) NOT NULL
);
