CREATE TABLE Currency (
    code VARCHAR(5) PRIMARY KEY,
    name VARCHAR(300) NOT NULL,
    exchangeRate DOUBLE NOT NULL,
    updateTime VARCHAR(30) NOT NULL
);