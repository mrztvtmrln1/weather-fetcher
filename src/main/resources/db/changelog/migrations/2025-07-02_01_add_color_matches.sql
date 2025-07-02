-- changeset temirlan:create_compatible_colors_table
CREATE TABLE compatible_colors (
    id BIGSERIAL PRIMARY KEY,
    color_one VARCHAR(50) NOT NULL,
    color_two VARCHAR(50) NOT NULL
);
