-- changeset temirlan:2025-04-17_1_init_weather_schema splitStatements:false endDelimiter=;

CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE weather (
    id SERIAL PRIMARY KEY,
    city_id INTEGER NOT NULL REFERENCES cities(id),
    temperature DECIMAL(5,2) NOT NULL,
    pressure INTEGER,
    humidity INTEGER,
    wind_speed DECIMAL(5,2),
    description TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



