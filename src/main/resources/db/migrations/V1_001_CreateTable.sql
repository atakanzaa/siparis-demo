CREATE TABLE settings (
                          id BIGSERIAL PRIMARY KEY,
                          key VARCHAR(255) NOT NULL,
                          value VARCHAR(255) NOT NULL
);