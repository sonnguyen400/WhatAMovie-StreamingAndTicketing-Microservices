--liquibase formatted sql
--changeset sonnguyen:20260321-create-address-tables

-- Country table
CREATE TABLE IF NOT EXISTS country
(
    id
    UUID
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    code VARCHAR
(
    10
) NOT NULL,
    phone_code VARCHAR
(
    10
),
    deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR
(
    255
),
    created_at TIMESTAMP DEFAULT NOW
(
),
    last_modified_by VARCHAR
(
    255
),
    last_modified_at TIMESTAMP DEFAULT NOW
(
)
    );

CREATE UNIQUE INDEX IF NOT EXISTS idx_country_code ON country(code) WHERE deleted = false;
CREATE INDEX IF NOT EXISTS idx_country_deleted ON country(deleted);

-- Administrative Region table (self-referencing tree)
CREATE TABLE IF NOT EXISTS administrative_region
(
    id
    UUID
    PRIMARY
    KEY,
    country_id
    UUID
    NOT
    NULL
    REFERENCES
    country
(
    id
),
    parent_id UUID REFERENCES administrative_region
(
    id
),
    name VARCHAR
(
    255
) NOT NULL,
    code VARCHAR
(
    50
),
    type VARCHAR
(
    50
) NOT NULL,
    level INTEGER NOT NULL DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR
(
    255
),
    created_at TIMESTAMP DEFAULT NOW
(
),
    last_modified_by VARCHAR
(
    255
),
    last_modified_at TIMESTAMP DEFAULT NOW
(
)
    );

CREATE INDEX IF NOT EXISTS idx_admin_region_country_id ON administrative_region(country_id);
CREATE INDEX IF NOT EXISTS idx_admin_region_parent_id ON administrative_region(parent_id);
CREATE INDEX IF NOT EXISTS idx_admin_region_level ON administrative_region(level);
CREATE INDEX IF NOT EXISTS idx_admin_region_type ON administrative_region(type);
CREATE INDEX IF NOT EXISTS idx_admin_region_deleted ON administrative_region(deleted);
CREATE INDEX IF NOT EXISTS idx_admin_region_code ON administrative_region(code);
