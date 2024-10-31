ALTER TABLE Customers
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INTEGER DEFAULT 1;

ALTER TABLE Products
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INTEGER DEFAULT 1;

ALTER TABLE Orders
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INTEGER DEFAULT 1;

ALTER TABLE Order_Items
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INTEGER DEFAULT 1;

ALTER TABLE Payments
    ADD COLUMN created_by VARCHAR(50),
    ADD COLUMN updated_by VARCHAR(50),
    ADD COLUMN deleted_by VARCHAR(50),
    ADD COLUMN deleted_at TIMESTAMP,
    ADD COLUMN version INTEGER DEFAULT 1;
