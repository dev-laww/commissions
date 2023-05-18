import tkinter as tk
from tkinter import messagebox
from tkinter import ttk

import database_handler
import models


class Dashboard(tk.Frame):
    def __init__(self, master):
        tk.Frame.__init__(self, master)
        self.parent = master

        columns = ("id", "employee_number", "employee_name", "rank")

        self.table = ttk.Treeview(self, columns=columns, show='headings')

        self.table.heading("id", text="id")
        self.table.heading("employee_number", text="Employee No.")
        self.table.heading("employee_name", text="Employee Name")
        self.table.heading("rank", text="Rank/Position")

        self.search_field = tk.Entry(self)
        self.add_button = tk.Button(self, text="Add Employee", command=self.go_to_add_form)
        self.delete_button = tk.Button(self, text="Delete Employee", command=self.delete_employee)
        self.update_button = tk.Button(self, text="Update Employee", command=self.go_to_update_form)

        self.update_table()

        self.search_field.bind('<KeyRelease>', self.update_table)

        tk.Label(self, text="Search by Name: ").grid(row=0, column=0, sticky='e')
        self.search_field.grid(row=0, column=1, columnspan=2, sticky='we', padx=10, pady=10)
        self.table.grid(row=1, column=0, columnspan=3)
        self.add_button.grid(row=2, column=0)
        self.update_button.grid(row=2, column=1)
        self.delete_button.grid(row=2, column=2)

    def update_table(self, event=None):
        self.get_employee_list()

        self.table.delete(*self.table.get_children())

        for employee in self.employee_list:
            row = (employee.id, employee.employee_number, employee.employee_name, employee.rank)
            self.table.insert('', tk.END, values=row)

    def get_employee_list(self):
        key = self.search_field.get()
        db_conn = database_handler.DBHandler()
        self.employee_list = db_conn.search_employee(key)
        db_conn.close()

    def delete_employee(self):
        selected_items = self.table.selection()
        if len(selected_items) == 0:
            messagebox.showwarning("Delete Employee", "Select an employee to delete.")
            return

        proceed = messagebox.askyesno("Delete Employee", "Do you want to delete the selected data?")

        if not proceed:
            return

        for item in selected_items:
            id = self.table.item(item)['values'][0]

            db_conn = database_handler.DBHandler()
            db_conn.delete_employee(id)
            db_conn.close()

            self.update_table()

    def go_to_update_form(self):
        selected_items = self.table.selection()
        if len(selected_items) == 0:
            messagebox.showwarning("Update Employee", "Select an employee to update.")
            return

        for item in selected_items:
            id = self.table.item(item)['values'][0]
            self.parent.change_window('Update_Employee', employee_id=id)

    def go_to_add_form(self):
        self.parent.change_window('Add_Employee')

    def on_return(self):
        self.update_table()


class Add_Form(tk.Frame):
    def __init__(self, master):
        tk.Frame.__init__(self, master)
        self.parent = master

        self.number_field = tk.Entry(self)
        self.name_field = tk.Entry(self)
        self.age_field = tk.Entry(self)
        self.rank_field = tk.Entry(self)
        self.salary_rate_field = tk.Entry(self)
        self.contact_number_field = tk.Entry(self)
        self.save_button = tk.Button(self, text="Save", command=self.save_employee)

        label_Texts = ["Employee Number: ", "Employee Name: ", "Age: ", "Rank: ", "Salary Rate: ", "Contact Number: "]

        for i in range(len(label_Texts)):
            tk.Label(self, text=label_Texts[i]).grid(row=i, column=0, sticky='e')

        self.number_field.grid(row=0, column=1)
        self.name_field.grid(row=1, column=1)
        self.age_field.grid(row=2, column=1)
        self.rank_field.grid(row=3, column=1)
        self.salary_rate_field.grid(row=4, column=1)
        self.contact_number_field.grid(row=5, column=1)
        self.save_button.grid(row=6, column=1, sticky='e', padx=20, pady=10)

    def validate_date(self):
        # Input validation
        return True

    def save_employee(self):
        if not self.validate_date():
            return
        try:
            new_employee = models.Employee()
            new_employee.employee_number = self.number_field.get()
            new_employee.employee_name = self.name_field.get()
            new_employee.age = int(self.age_field.get())
            new_employee.rank = self.rank_field.get()
            new_employee.salary_rate = float(self.salary_rate_field.get())
            new_employee.contact_number = self.contact_number_field.get()

            db_conn = database_handler.DBHandler()
            db_conn.add_employee(new_employee)
            db_conn.close()

            self.go_to_dashboard()
        except ValueError:
            messagebox.showerror("Add Employee", "Age and Salary Rate must be numbers")

    def go_to_dashboard(self):
        self.parent.change_window('Dashboard')

    def on_return(self):
        self.name_field.delete(0, tk.END)
        self.number_field.delete(0, tk.END)
        self.age_field.delete(0, tk.END)
        self.rank_field.delete(0, tk.END)
        self.salary_rate_field.delete(0, tk.END)
        self.contact_number_field.delete(0, tk.END)


class Update_Form(tk.Frame):
    def __init__(self, master):
        tk.Frame.__init__(self, master)
        self.parent = master

        self.id_label = tk.Label(self, text="XX")
        self.number_field = tk.Entry(self)
        self.name_field = tk.Entry(self)
        self.age_field = tk.Entry(self)
        self.rank_field = tk.Entry(self)
        self.salary_rate_field = tk.Entry(self)
        self.contact_number_field = tk.Entry(self)
        self.save_button = tk.Button(self, text="Update", command=self.update_employee)

        label_Texts = ["ID: ", "Employee Number: ", "Employee Name: ", "Age: ", "Rank: ", "Salary Rate: ",
                       "Contact Number: "]

        for i in range(len(label_Texts)):
            tk.Label(self, text=label_Texts[i]).grid(row=i, column=0, sticky='e')

        self.id_label.grid(row=0, column=1)
        self.number_field.grid(row=1, column=1)
        self.name_field.grid(row=2, column=1)
        self.age_field.grid(row=3, column=1)
        self.rank_field.grid(row=4, column=1)
        self.salary_rate_field.grid(row=5, column=1)
        self.contact_number_field.grid(row=6, column=1)
        self.save_button.grid(row=7, column=1, sticky='e', padx=20, pady=10)

    def on_return(self, **kwargs):
        self.id = kwargs['employee_id']
        self.id_label.config(text=self.id)

        db_conn = database_handler.DBHandler()
        employee_data = db_conn.read_one_employee(self.id)
        db_conn.close

        self.clear_fields()

        self.number_field.insert(0, employee_data.employee_number)
        self.name_field.insert(0, employee_data.employee_name)
        self.age_field.insert(0, employee_data.age)
        self.rank_field.insert(0, employee_data.rank)
        self.salary_rate_field.insert(0, employee_data.salary_rate)
        self.contact_number_field.insert(0, employee_data.contact_number)

    def clear_fields(self):
        self.name_field.delete(0, tk.END)
        self.number_field.delete(0, tk.END)
        self.age_field.delete(0, tk.END)
        self.rank_field.delete(0, tk.END)
        self.salary_rate_field.delete(0, tk.END)
        self.contact_number_field.delete(0, tk.END)

    def validate_date(self):
        # Input validation
        return True

    def update_employee(self):
        if not self.validate_date():
            return
        try:
            new_employee = models.Employee()
            new_employee.id = self.id
            new_employee.employee_number = self.number_field.get()
            new_employee.employee_name = self.name_field.get()
            new_employee.age = int(self.age_field.get())
            new_employee.rank = self.rank_field.get()
            new_employee.salary_rate = float(self.salary_rate_field.get())
            new_employee.contact_number = self.contact_number_field.get()

            proceed = messagebox.askyesno("Update Employee", "Do you want to commit changes to the selected data?")

            if not proceed:
                return

            db_conn = database_handler.DBHandler()
            db_conn.update_employee(new_employee)
            db_conn.close()

            self.go_to_dashboard()
        except ValueError:
            messagebox.showerror("Update Employee", "Age and Salary Rate must be numbers")

    def go_to_dashboard(self):
        self.parent.change_window('Dashboard')
