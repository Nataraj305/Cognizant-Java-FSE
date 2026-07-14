----------------------------------------------------
-- Scenario 1 : Apply Interest Discount
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
            'Discount applied for Customer ID: '
            || cust_rec.CustomerID
        );

    END LOOP;

    COMMIT;

END;
/

----------------------------------------------------
-- Verify Output
----------------------------------------------------

SELECT *
FROM Loans
ORDER BY LoanID;

----------------------------------------------------
-- Scenario 2 : Update VIP Status
----------------------------------------------------

DECLARE

    CURSOR vip_cursor IS
        SELECT CustomerID
        FROM Customers
        WHERE Balance > 10000;

BEGIN

    FOR vip_rec IN vip_cursor LOOP

        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = vip_rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE(
            'VIP Status Granted to Customer ID: '
            || vip_rec.CustomerID
        );

    END LOOP;

    COMMIT;

END;
/

----------------------------------------------------
-- Verify Output
----------------------------------------------------

SELECT *
FROM Customers
ORDER BY CustomerID;

----------------------------------------------------
-- Scenario 3 : Generate Loan Reminder
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
            ON c.CustomerID = l.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN

    FOR loan_rec IN loan_cursor LOOP

        DBMS_OUTPUT.PUT_LINE(

            'Reminder: Dear '
            || loan_rec.Name
            || ', your Loan ID '
            || loan_rec.LoanID
            || ' is due on '
            || TO_CHAR(
                    loan_rec.DueDate,
                    'DD-MON-YYYY'
               )

        );

    END LOOP;

END;
/