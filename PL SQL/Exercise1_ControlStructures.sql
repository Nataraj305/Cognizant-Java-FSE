----------------------------------------------------
-- Create Customers Table
----------------------------------------------------

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(50),
    Age NUMBER,
    Balance NUMBER,
    IsVIP VARCHAR2(5)
);

----------------------------------------------------
-- Create Loans Table
----------------------------------------------------

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    InterestRate NUMBER,
    DueDate DATE,
    FOREIGN KEY (CustomerID)
    REFERENCES Customers(CustomerID)
);

----------------------------------------------------
-- Insert Sample Data
----------------------------------------------------

INSERT INTO Customers VALUES (101,'John',65,12000,'FALSE');
INSERT INTO Customers VALUES (102,'Mary',45,15000,'FALSE');
INSERT INTO Customers VALUES (103,'David',70,8000,'FALSE');
INSERT INTO Customers VALUES (104,'Alice',30,20000,'FALSE');

INSERT INTO Loans VALUES (1,101,10,DATE '2026-07-10');
INSERT INTO Loans VALUES (2,102,9,DATE '2026-08-25');
INSERT INTO Loans VALUES (3,103,11,DATE '2026-07-20');
INSERT INTO Loans VALUES (4,104,8,DATE '2026-09-15');

COMMIT;

----------------------------------------------------
-- Scenario 1
-- Apply 1% Interest Discount
----------------------------------------------------

DECLARE

    CURSOR cust_cursor IS
        SELECT CustomerID
        FROM Customers
        WHERE Age > 60;

BEGIN

    FOR cust_rec IN cust_cursor LOOP

        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE CustomerID = cust_rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE(
            'Discount applied for Customer ID : '
            || cust_rec.CustomerID
        );

    END LOOP;

    COMMIT;

END;
/

----------------------------------------------------
-- Scenario 2
-- Promote Customers to VIP
----------------------------------------------------

DECLARE

    CURSOR vip_cursor IS
        SELECT CustomerID
        FROM Customers
        WHERE Balance > 10000;

BEGIN

    FOR vip_rec IN vip_cursor LOOP

        UPDATE Customers
        SET IsVIP='TRUE'
        WHERE CustomerID = vip_rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE(
            'VIP Status Granted to Customer ID : '
            || vip_rec.CustomerID
        );

    END LOOP;

    COMMIT;

END;
/

----------------------------------------------------
-- Scenario 3
-- Loan Due Reminder
----------------------------------------------------

DECLARE

    CURSOR loan_cursor IS

        SELECT
            c.CustomerID,
            c.Name,
            l.LoanID,
            l.DueDate

        FROM Customers c

        JOIN Loans l
        ON c.CustomerID=l.CustomerID

        WHERE l.DueDate
        BETWEEN SYSDATE AND SYSDATE+30;

BEGIN

    FOR loan_rec IN loan_cursor LOOP

        DBMS_OUTPUT.PUT_LINE(

            'Reminder: Dear '
            || loan_rec.Name
            || ', your Loan ID '
            || loan_rec.LoanID
            || ' is due on '
            || TO_CHAR(loan_rec.DueDate,'DD-MON-YYYY')

        );

    END LOOP;

END;
/