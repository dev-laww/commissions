import sqlite3
from tkinter import messagebox

import models


class DbHandler:
    def __init__(self):
        self.conn = sqlite3.connect('database.db')
        self.employee_table = "Employee_table"
        self.salary_table = "Salary_table"

    def read_employee(self):
        cursor = self.conn.cursor()

        query = f"SELECT * FROM {self.employee_table}"

        cursor.execute(query)
        employee_list = []
        for employee_id, name, number, age, salary_rate, contact in cursor:
            new_employee = models.Employee(name, number, age, salary_rate, contact, employee_id)
            employee_list.append(new_employee)

        return employee_list

    def add_employee(self, employee):
        cursor = self.conn.cursor()
        query = f"INSERT INTO {self.employee_table} (employee_name, employee_number, age, salary_rate, contact_number) VALUES (?, ?, ?, ?, ?)"
        values = (
            employee.employee_name,
            employee.employee_number,
            employee.age,
            employee.salary_rate,
            employee.contact_number
        )
        cursor.execute(query, values)
        self.conn.commit()

    def delete_employee(self, employee_id):
        cursor = self.conn.cursor()
        cursor.execute(f"SELECT * FROM {self.salary_table} WHERE id = ?", (employee_id,))

        if len(cursor.fetchall()) < 0:
            raise Exception("Employee does not exist")

        delete_query = f"DELETE FROM {self.employee_table} WHERE id = ?"
        cursor.execute(delete_query, (employee_id,))
        self.conn.commit()
        messagebox.showinfo("Delete Employee", "Employee deleted.")

    def close(self):
        self.conn.close()
