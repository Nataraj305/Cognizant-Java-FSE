----------------------------------------------------
-- Package Specification
----------------------------------------------------

CREATE OR REPLACE PACKAGE BankPackage AS

    ------------------------------------------------
    -- Transfer Funds
    ------------------------------------------------
    PROCEDURE SafeTransferFunds
    (
        p_fromAccount IN NUMBER,
        p_toAccount IN NUMBER,
        p_amount IN NUMBER
    );

    ------------------------------------------------
    -- Update Employee Salary
    ------------------------------------------------
    PROCEDURE UpdateSalary
    (
        p_employeeID IN NUMBER,
        p_increment IN NUMBER
    );

END BankPackage;
/

----------------------------------------------------
-- Package Body
----------------------------------------------------

CREATE OR REPLACE PACKAGE BODY BankPackage AS

    ------------------------------------------------
    -- Procedure 1 : SafeTransferFunds
    ----------------------------------------------------

    PROCEDURE SafeTransferFunds
    (
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
                'Insufficient balance.'
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

    END SafeTransferFunds;

    ----------------------------------------------------
    -- Procedure 2 : UpdateSalary
    ----------------------------------------------------

    PROCEDURE UpdateSalary
    (
        p_employeeID IN NUMBER,
        p_increment IN NUMBER
    )
    IS
    BEGIN

        UPDATE Employees
        SET Salary = Salary + p_increment
        WHERE EmployeeID = p_employeeID;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(
                -20002,
                'Employee does not exist.'
            );
        END IF;

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Salary Updated Successfully.');

    END UpdateSalary;

END BankPackage;
/

----------------------------------------------------
-- Test Case 1 : SafeTransferFunds
----------------------------------------------------

BEGIN

    BankPackage.SafeTransferFunds
    (
        101,
        102,
        1000
    );

END;
/

----------------------------------------------------
-- Verify Output
----------------------------------------------------

SELECT *
FROM Accounts
ORDER BY AccountID;

----------------------------------------------------
-- Test Case 2 : UpdateSalary
----------------------------------------------------

BEGIN

    BankPackage.UpdateSalary
    (
        1001,
        1000
    );

END;
/

----------------------------------------------------
-- Verify Output
----------------------------------------------------

SELECT *
FROM Employees
ORDER BY EmployeeID;