----------------------------------------------------
-- Create Accounts Table
----------------------------------------------------

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(50),
    Balance NUMBER(10,2)
);

----------------------------------------------------
-- Create Employees Table
----------------------------------------------------

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    EmployeeName VARCHAR2(50),
    Salary NUMBER(10,2)
);

----------------------------------------------------
-- Insert Sample Data
----------------------------------------------------

INSERT INTO Accounts VALUES (101,'John',20000);
INSERT INTO Accounts VALUES (102,'David',15000);
INSERT INTO Accounts VALUES (103,'Smith',5000);

INSERT INTO Employees VALUES (1001,'Alice',50000);
INSERT INTO Employees VALUES (1002,'Bob',60000);

COMMIT;

----------------------------------------------------
-- Scenario 1
-- SafeTransferFunds
----------------------------------------------------

CREATE OR REPLACE PROCEDURE SafeTransferFunds (

    p_fromAccount IN NUMBER,
    p_toAccount IN NUMBER,
    p_amount IN NUMBER

)
IS

    v_balance NUMBER;

BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_fromAccount;

    IF v_balance < p_amount THEN

        RAISE_APPLICATION_ERROR(
            -20001,
            'Insufficient balance for transfer.'
        );

    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_fromAccount;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_toAccount;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer Successful.');

EXCEPTION

    WHEN NO_DATA_FOUND THEN

        DBMS_OUTPUT.PUT_LINE('Account not found.');

        ROLLBACK;

    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

        ROLLBACK;

END;
/

----------------------------------------------------
-- Test Case
----------------------------------------------------

BEGIN
    SafeTransferFunds(101,102,5000);
END;
/

BEGIN
    SafeTransferFunds(103,101,10000);
END;
/

----------------------------------------------------
-- Scenario 2
-- UpdateSalary
----------------------------------------------------

CREATE OR REPLACE PROCEDURE UpdateSalary (

    p_employeeID IN NUMBER,
    p_increment IN NUMBER

)
IS

    v_count NUMBER;

BEGIN

    SELECT COUNT(*)
    INTO v_count
    FROM Employees
    WHERE EmployeeID = p_employeeID;

    IF v_count = 0 THEN

        RAISE_APPLICATION_ERROR(
            -20002,
            'Employee does not exist.'
        );

    END IF;

    UPDATE Employees
    SET Salary = Salary + p_increment
    WHERE EmployeeID = p_employeeID;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary Updated Successfully.');

EXCEPTION

    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

        ROLLBACK;

END;
/

----------------------------------------------------
-- Test Case
----------------------------------------------------

BEGIN
    UpdateSalary(1001,5000);
END;
/

BEGIN
    UpdateSalary(9999,5000);
END;
/

----------------------------------------------------
-- Scenario 3
-- AddNewCustomer
----------------------------------------------------

CREATE OR REPLACE PROCEDURE AddNewCustomer (

    p_customerID IN NUMBER,
    p_name IN VARCHAR2,
    p_age IN NUMBER,
    p_balance IN NUMBER,
    p_isVIP IN VARCHAR2

)
IS

BEGIN

    INSERT INTO Customers
    VALUES (
        p_customerID,
        p_name,
        p_age,
        p_balance,
        p_isVIP
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer Added Successfully.');

EXCEPTION

    WHEN DUP_VAL_ON_INDEX THEN

        DBMS_OUTPUT.PUT_LINE('Customer ID already exists.');

    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END;
/

----------------------------------------------------
-- Test Case
----------------------------------------------------

BEGIN
    AddNewCustomer(
        105,
        'Rahul',
        28,
        18000,
        'FALSE'
    );
END;
/

BEGIN
    AddNewCustomer(
        105,
        'Rahul',
        28,
        18000,
        'FALSE'
    );
END;
/

----------------------------------------------------
-- Verify Output
----------------------------------------------------

SELECT * FROM Accounts ORDER BY AccountID;

SELECT * FROM Employees ORDER BY EmployeeID;

SELECT * FROM Customers ORDER BY CustomerID;