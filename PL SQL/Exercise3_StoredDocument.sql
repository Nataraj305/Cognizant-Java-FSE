----------------------------------------------------
-- Scenario 1 : Process Monthly Interest
----------------------------------------------------

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN

    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Monthly Interest Processed Successfully.');

EXCEPTION

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
        ROLLBACK;

END;
----------------------------------------------------
-- Test Case
----------------------------------------------------

BEGIN
    ProcessMonthlyInterest;
END;
----------------------------------------------------
-- Verify Output
----------------------------------------------------
SELECT * FROM Accounts
ORDER BY AccountID;
----------------------------------------------------
-- Scenario 2 : Update Employee Bonus
----------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (

    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
)
IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE Department = p_department;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Employee Bonus Updated Successfully.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
        ROLLBACK;
END;
----------------------------------------------------
-- Test Case
----------------------------------------------------
BEGIN
    UpdateEmployeeBonus('IT',10);
END;
----------------------------------------------------
-- Verify Output
----------------------------------------------------
SELECT * FROM Employees
ORDER BY EmployeeID;
----------------------------------------------------
-- Scenario 3 : Transfer Funds
----------------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_fromAccount IN NUMBER,
    p_toAccount IN NUMBER,
    p_amount IN NUMBER
)
IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_fromAccount;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_toAccount;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Funds Transferred Successfully.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
        ROLLBACK;
END;
----------------------------------------------------
-- Test Case
----------------------------------------------------
BEGIN
    TransferFunds(101,103,1000);
END;
----------------------------------------------------
-- Verify Output
----------------------------------------------------
SELECT * FROM Accounts
ORDER BY AccountID;