/*==========================================================
        PLSQL HANDS-ON
        Topic : Stored Procedures
==========================================================*/

CREATE TABLE Employee (
    EmpID NUMBER PRIMARY KEY,
    EmpName VARCHAR2(50),
    Department VARCHAR2(30),
    Salary NUMBER(10,2)
);

INSERT INTO Employee VALUES (101,'Rahul','IT',50000);
INSERT INTO Employee VALUES (102,'Anjali','HR',42000);
INSERT INTO Employee VALUES (103,'Amit','Finance',60000);
INSERT INTO Employee VALUES (104,'Neha','IT',55000);

COMMIT;

SELECT * FROM Employee;

CREATE OR REPLACE PROCEDURE Welcome_Message
AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Welcome to PL/SQL Stored Procedures');
END;
/

BEGIN
    Welcome_Message;
END;
/

CREATE OR REPLACE PROCEDURE GetEmployee
(
    p_EmpID IN NUMBER
)
AS
    v_Name Employee.EmpName%TYPE;
    v_Salary Employee.Salary%TYPE;
BEGIN
    SELECT EmpName, Salary
    INTO v_Name, v_Salary
    FROM Employee
    WHERE EmpID = p_EmpID;

    DBMS_OUTPUT.PUT_LINE('Employee Name : ' || v_Name);
    DBMS_OUTPUT.PUT_LINE('Salary        : ' || v_Salary);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee Not Found');
END;
/

BEGIN
    GetEmployee(101);
END;
/

CREATE OR REPLACE PROCEDURE IncreaseSalary
(
    p_EmpID IN NUMBER,
    p_Amount IN NUMBER
)
AS
BEGIN
    UPDATE Employee
    SET Salary = Salary + p_Amount
    WHERE EmpID = p_EmpID;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary Updated Successfully');
END;
/

BEGIN
    IncreaseSalary(101,5000);
END;
/

SELECT * FROM Employee;

CREATE OR REPLACE PROCEDURE GetSalary
(
    p_EmpID IN NUMBER,
    p_Salary OUT NUMBER
)
AS
BEGIN
    SELECT Salary
    INTO p_Salary
    FROM Employee
    WHERE EmpID = p_EmpID;
END;
/

DECLARE
    v_Salary NUMBER;
BEGIN
    GetSalary(102,v_Salary);
    DBMS_OUTPUT.PUT_LINE('Salary : ' || v_Salary);
END;
/

CREATE OR REPLACE PROCEDURE BonusSalary
(
    p_Salary IN OUT NUMBER
)
AS
BEGIN
    p_Salary := p_Salary + 3000;
END;
/

DECLARE
    v_Salary NUMBER := 40000;
BEGIN
    BonusSalary(v_Salary);
    DBMS_OUTPUT.PUT_LINE('New Salary : ' || v_Salary);
END;
/

CREATE OR REPLACE PROCEDURE DeleteEmployee
(
    p_EmpID NUMBER
)
AS
BEGIN
    DELETE FROM Employee
    WHERE EmpID = p_EmpID;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Employee Deleted');
END;
/

BEGIN
    DeleteEmployee(104);
END;
/

SELECT * FROM Employee;

CREATE OR REPLACE PROCEDURE SalaryCategory
(
    p_EmpID NUMBER
)
AS
    v_Salary NUMBER;
BEGIN
    SELECT Salary
    INTO v_Salary
    FROM Employee
    WHERE EmpID = p_EmpID;

    IF v_Salary >= 60000 THEN
        DBMS_OUTPUT.PUT_LINE('High Salary');
    ELSIF v_Salary >= 50000 THEN
        DBMS_OUTPUT.PUT_LINE('Medium Salary');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Low Salary');
    END IF;
END;
/

BEGIN
    SalaryCategory(101);
END;
/

CREATE OR REPLACE PROCEDURE DisplayEmployees
AS
CURSOR emp_cursor IS
SELECT * FROM Employee;

v_emp Employee%ROWTYPE;
BEGIN
OPEN emp_cursor;

LOOP
    FETCH emp_cursor INTO v_emp;
    EXIT WHEN emp_cursor%NOTFOUND;

    DBMS_OUTPUT.PUT_LINE(
        v_emp.EmpID || ' ' ||
        v_emp.EmpName || ' ' ||
        v_emp.Department || ' ' ||
        v_emp.Salary);
END LOOP;

CLOSE emp_cursor;
END;
/

BEGIN
    DisplayEmployees;
END;
/

DROP PROCEDURE Welcome_Message;
