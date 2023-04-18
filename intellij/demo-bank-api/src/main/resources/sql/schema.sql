DROP TABLE IF EXISTS banks;

CREATE TABLE banks
(
    id IDENTITY NOT NULL PRIMARY KEY,
    bank_name       varchar NOT NULL,
    account_number  varchar NOT NULL,
    trust           numeric(28, 14),
    transaction_fee int
)