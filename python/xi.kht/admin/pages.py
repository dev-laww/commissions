import re
import tkinter as tk
from tkinter import messagebox
from tkinter import ttk

import database_handler
import models


class DashBoard(tk.Frame):
    def __init__(self, parent):
        tk.Frame.__init__(self, parent)
        self.employee_list = None
        self.parent = parent
        columns = ("id", "employee_number", "employee_name")
        self.tree = ttk.Treeview(self, columns=columns, show='headings', height=5)
        self.tree.heading("id", text="id")
        self.tree.heading('employee_number', text='Number')
        self.tree.heading('employee_name', text='Name')

        self.update_table()

        self.add_button = tk.Button(self, text="Add", command=self.go_to_add)
        self.delete_button = tk.Button(self, text="Delete", command=self.go_to_delete)
        self.tree.grid(row=0, column=0, columnspan=2)
        self.add_button.grid(row=1, column=0)
        self.delete_button.grid(row=1, column=1)

    def update_table(self):
        self.get_employee_list()
        self.tree.delete(*self.tree.get_children())
        for employee in self.employee_list:
            row = (employee.id, employee.employee_number, employee.employee_name)
            self.tree.insert('', tk.END, values=row)

    def get_employee_list(self):
        db = database_handler.DbHandler()
        self.employee_list = db.read_employee()
        db.close()

    def go_to_add(self):
        self.parent.change_frame("Add_Employee")

    def go_to_delete(self):
        self.parent.change_frame("Delete_Employee")

    def on_return(self):
        self.update_table()


class EmployeeForm(tk.Frame):
    def __init__(self, parent):
        tk.Frame.__init__(self, parent)
        self.parent = parent
        self.name_field = tk.Entry(self)
        self.number_field = tk.Entry(self)
        self.age_field = tk.Entry(self)
        self.salary_rate_field = tk.Entry(self)
        self.contact_number_field = tk.Entry(self)
        self.save_button = tk.Button(self, text="Save", command=self.save_data)

        tk.Label(self, text="Employee number: ").grid(row=0, column=0)
        self.number_field.grid(row=0, column=1)
        tk.Label(self, text="Employee name: ").grid(row=1, column=0)
        self.name_field.grid(row=1, column=1)
        tk.Label(self, text="Age: ").grid(row=2, column=0)
        self.age_field.grid(row=2, column=1)
        tk.Label(self, text="Salary Rate: ").grid(row=3, column=0)
        self.salary_rate_field.grid(row=3, column=1)
        tk.Label(self, text="Contact Number: ").grid(row=4, column=0)
        self.contact_number_field.grid(row=4, column=1)
        self.save_button.grid(row=5, column=0, columnspan=2)

    def save_data(self):
        try:
            age = int(self.age_field.get())
            name = self.name_field.get()

            if not re.match(r"^[a-zA-Z ]+$", name):
                raise Exception("Invalid Name")

            if age < 18:
                raise ValueError

            salary_rate = float(self.salary_rate_field.get())

            employee = models.Employee(
                name,
                self.number_field.get(),
                age,
                salary_rate,
                self.contact_number_field.get()
            )

            db = database_handler.DbHandler()
            db.add_employee(employee)
            db.close()
            messagebox.showinfo("Add Employee", "Employee added successfully.")
            self.go_to_dashboard()
        except ValueError:
            messagebox.showerror("Add Employee", "Invalid Inputs.")

    def go_to_dashboard(self):
        self.parent.change_frame("Dashboard")

    def on_return(self):
        self.name_field.delete(0, 'end')
        self.number_field.delete(0, 'end')
        self.age_field.delete(0, 'end')
        self.salary_rate_field.delete(0, 'end')
        self.contact_number_field.delete(0, 'end')


class DeleteEmployee(tk.Frame):
    def __init__(self, parent):
        tk.Frame.__init__(self)
        self.parent = parent
        self.id_field = tk.Entry(self)
        self.delete_button = tk.Button(self, text="Delete", command=self.delete_data)

        tk.Label(self, text="Employee ID: ").grid(row=0, column=0)
        self.id_field.grid(row=0, column=1)
        self.delete_button.grid(row=1, column=0, columnspan=2)

    def delete_data(self):
        try:
            employee_id = int(self.id_field.get())
            db = database_handler.DbHandler()
            db.delete_employee(employee_id)
            db.close()
            self.go_to_dashboard()
        except ValueError:
            messagebox.showerror("Delete Employee", "Invalid Inputs.")
        except Exception as e:
            messagebox.showerror("Delete Employee", str(e))

    def go_to_dashboard(self):
        self.parent.change_frame("Dashboard")

    def on_return(self):
        self.id_field.delete(0, 'end')
