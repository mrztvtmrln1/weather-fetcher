CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,

                        user_id BIGINT NOT NULL REFERENCES users(user_id),

                        status VARCHAR(50) NOT NULL,

                        total_amount NUMERIC(12, 2) NOT NULL DEFAULT 0,

                        payment_type VARCHAR(50),

                        delivery_address TEXT,
                        delivery_status VARCHAR(50),

                        created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                        updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);