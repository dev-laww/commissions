import sqlite3

import models


class DBHandler:
    def __init__(self):
        self.db_name = 'database.db'
        self.employee_table = 'Employee_table'
        self.salary_table = 'Salary_table'

        self.conn = sqlite3.connect(self.db_name)
        self.cursor = self.conn.cursor()

    def read_employee(self):
        employees = []

        query = f'SELECT * FROM {self.employee_table} ORDER BY employee_name'
        self.cursor.execute(query)

        for row in self.cursor:
            new_employee = models.Employee()
            new_employee.id = row[0]
            new_employee.employee_number = row[1]
            new_employee.employee_name = row[2]
            new_employee.age = row[3]
            new_employee.rank = row[4]
            new_employee.salary_rate = row[5]
            new_employee.contact_number = row[6]
            employees.append(new_employee)

        return employees

    def add_employee(self, employee: models.Employee):
        query = "INSERT INTO Employee_table (employee_number, employee_name, age, rank, salary_rate, contact_number) VALUES (?, ?, ?, ?, ?, ?)"
        values = (employee.employee_number, employee.employee_name, employee.age, employee.rank, employee.salary_rate,
                  employee.contact_number)
        self.cursor.execute(query, values)
        self.conn.commit()

    def delete_employee(self, id: int):
        query = f"DELETE FROM {self.employee_table} WHERE id = ?"
        values = (id,)

        self.cursor.execute(query, values)
        self.conn.commit()

    def read_one_employee(self, id: int):
        query = f"SELECT * FROM {self.employee_table} WHERE id = ?"
        values = (id,)
        self.cursor.execute(query, values)

        for row in self.cursor:
            new_employee = models.Employee()
            new_employee.id = row[0]
            new_employee.employee_number = row[1]
            new_employee.employee_name = row[2]
            new_employee.age = row[3]
            new_employee.rank = row[4]
            new_employee.salary_rate = row[5]
            new_employee.contact_number = row[6]
            new_employee.payrolls = self.read_payroll(new_employee.id)
            return new_employee

    def update_employee(self, employee: models.Employee):
        query = f"UPDATE Employee_table SET employee_number = ?, employee_name = ?, age = ?, rank = ?, salary_rate = ?, contact_number= ? WHERE id = ?"
        values = (employee.employee_number, employee.employee_name, employee.age, employee.rank, employee.salary_rate,
                  employee.contact_number, employee.id)
        self.cursor.execute(query, values)
        self.conn.commit()

    def search_employee(self, key):
        key = f'{key}%'
        query = f"SELECT * FROM {self.employee_table} WHERE employee_name LIKE ? ORDER BY employee_name"
        values = (key,)
        self.cursor.execute(query, values)

        employees = []
        for row in self.cursor:
            new_employee = models.Employee()
            new_employee.id = row[0]
            new_employee.employee_number = row[1]
            new_employee.employee_name = row[2]
            new_employee.age = row[3]
            new_employee.rank = row[4]
            new_employee.salary_rate = row[5]
            new_employee.contact_number = row[6]
            employees.append(new_employee)

        return employees

    def read_payroll(self, employee_id):
        query = f"SELECT * FROM {self.salary_table} WHERE employee_id = ?"
        values = (employee_id,)
        cursor = self.conn.cursor()

        cursor.execute(query, values)

        payrolls = []
        for row in cursor:
            new_payroll = models.Payroll()
            new_payroll.id = row[0]
            new_payroll.employee_id = row[1]
            new_payroll.date = row[2]
            new_payroll.rendered_hours = row[3]
            new_payroll.salary = row[4]
            payrolls.append(new_payroll)

        return payrolls

    def add_payroll(self, payroll: models.Payroll):
        query = "INSERT INTO Salary_table (employee_id, date, rendered_hours, salary) VALUES (?, ?, ?, ?)"
        values = (payroll.employee_id, payroll.date, payroll.rendered_hours, payroll.salary)
        self.cursor.execute(query, values)
        self.conn.commit()

    def close(self):
        self.conn.close()


DBHandler().read_employee()
