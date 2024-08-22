BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE device';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE support_ticket';
EXCEPTION
    WHEN OTHERS THEN
        NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE device_seq';
EXCEPTION
    WHEN OTHERS THEN
        NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE support_ticket_seq';
EXCEPTION
    WHEN OTHERS THEN
        NULL;
END;
/

-- Create tables

CREATE TABLE device (
    id NUMBER(10) NOT NULL,
    device_type VARCHAR2(45) NOT NULL,
    device_width NUMBER(10, 2) NOT NULL,
    device_height NUMBER(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE device_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER device_seq_tr
BEFORE INSERT ON device
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT device_seq.NEXTVAL INTO :NEW.id FROM DUAL;
    END IF;
END;
/

CREATE TABLE support_ticket (
    ticket_id NUMBER(10) NOT NULL,
    user_id NUMBER(10) NOT NULL,
    issue_description VARCHAR2(300) NOT NULL,
    status VARCHAR2(10) CHECK (status IN ('RESOLVED', 'OPEN')) NOT NULL,
    created_date DATE,
    resolved_date DATE,
    PRIMARY KEY (ticket_id)
);

CREATE SEQUENCE support_ticket_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER support_ticket_seq_tr
BEFORE INSERT ON support_ticket
FOR EACH ROW
BEGIN
    IF :NEW.ticket_id IS NULL THEN
        SELECT support_ticket_seq.NEXTVAL INTO :NEW.ticket_id FROM DUAL;
    END IF;
END;
/

-- Inserting data into the device table
INSERT INTO device (device_type, device_width, device_height)
VALUES ('smartPhone', 6.5, 5.0);

INSERT INTO device (device_type, device_width, device_height)
VALUES ('tablet', 7, 8);
Commit;

-- Inserting data into the support_ticket table
INSERT INTO support_ticket (user_id, issue_description, status, created_date, resolved_date)
VALUES (5678, 'Issue with UPI payment', 'OPEN', sysdate, NULL);

INSERT INTO support_ticket (user_id, issue_description, status, created_date, resolved_date)
VALUES (9001, 'Bank transfer failed', 'RESOLVED', sysdate, sysdate + 5);

INSERT INTO support_ticket (user_id, issue_description, status, created_date, resolved_date)
VALUES (5678, 'Unable to login', 'OPEN', sysdate, sysdate + 5);
Commit;