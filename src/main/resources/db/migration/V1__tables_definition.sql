CREATE TABLE IF NOT EXISTS companies
(
    id                          BIGSERIAL PRIMARY KEY,
    name                        VARCHAR(255),
    address                     VARCHAR(255),
    identity_number             VARCHAR(255),
    type                        VARCHAR(50),
    vat_number                  VARCHAR(50),
    responsible_officer_name    VARCHAR(255),
    bank_identifier_code        VARCHAR(50),
    bank_name                   VARCHAR(255),
    iban                        VARCHAR(50),
    deleted                     BOOL
);

CREATE TABLE IF NOT EXISTS invoices
(
    id             BIGSERIAL PRIMARY KEY,
    serial_number  BIGINT UNIQUE,
    provider_id    BIGINT REFERENCES companies (id),
    client_id      BIGINT REFERENCES companies (id),
    issue_date     DATE,
    issue_location VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS invoice_contents
(
    invoice_id            BIGINT NOT NULL REFERENCES invoices (id),
    service_description   VARCHAR(255),
    unit_price            REAL,
    quantity              INT,
    discount              REAL
);

INSERT INTO companies (name,
                       identity_number,
                       address,
                       type,
                       vat_number,
                       responsible_officer_name,
                       bank_identifier_code,
                       bank_name,
                       iban,
                       deleted)
VALUES ('АЛФА ТД',
        '203786770',
        'гр. Пловдив, ж.к Тракия, бл. 107, вх. Д, ет. 6',
        'SUPPLIER',
        '203786770',
        'Таню Димитров',
        'UBBSBGSF',
        'ОББ Банк',
        'BG93UBBS81551006599372',
        false);

CREATE SEQUENCE invoice_serial_seq
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;