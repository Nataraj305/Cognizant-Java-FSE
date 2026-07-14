----------------------------------------------------
-- Scenario 1 : Calculate Age
----------------------------------------------------

CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
)
RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);

    RETURN v_age;
END;
/

----------------------------------------------------
-- Test Case
----------------------------------------------------

SELECT CalculateAge(
    TO_DATE('15-05-1990','DD-MM-YYYY')
) AS Age
FROM Dual;

----------------------------------------------------
-- Scenario 2 : Calculate Monthly Installment
----------------------------------------------------

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_years NUMBER
)
RETURN NUMBER
IS
    v_r NUMBER;
    v_n NUMBER;
    v_power NUMBER;
    v_emi NUMBER;
BEGIN
    v_r := p_interest_rate / (12 * 100);
    v_n := p_years * 12;

    v_power := POWER(1 + v_r, v_n);

    v_emi := (p_loan_amount * v_r * v_power)
             / (v_power - 1);

    RETURN ROUND(v_emi, 2);
END;
/

----------------------------------------------------
-- Test Case
----------------------------------------------------

SELECT CalculateMonthlyInstallment(
    500000,
    10,
    5
) AS EMI
FROM Dual;

----------------------------------------------------
-- Scenario 3 : Check Sufficient Balance
----------------------------------------------------

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_accountID NUMBER,
    p_amount NUMBER
)
RETURN VARCHAR2
IS
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_accountID;

    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'FALSE';
END;
/

----------------------------------------------------
-- Test Cases
----------------------------------------------------

SELECT HasSufficientBalance(
    101,
    5000
) AS RESULT
FROM Dual;

SELECT HasSufficientBalance(
    103,
    10000
) AS RESULT
FROM Dual;