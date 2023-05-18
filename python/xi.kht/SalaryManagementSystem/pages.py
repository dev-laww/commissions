import re
import tkinter as tk
from datetime import datetime
from tkinter import messagebox
from tkinter import ttk
from tkcalendar import Calendar, DateEntry

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
        self.back_button = tk.Button(self, text="Back", command=self.go_to_dashboard)

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
        self.back_button.grid(row=6, column=1, sticky='w', padx=20, pady=10)

    def validate_date(self):
        employee_number = self.number_field.get()
        employee_name = self.name_field.get()
        age = self.age_field.get()
        rank = self.rank_field.get()
        salary_rate = self.salary_rate_field.get()
        contact_number = self.contact_number_field.get()

        # Perform validation checks
        if not employee_number or not employee_name or not age or not rank or not salary_rate or not contact_number:
            messagebox.showerror("Validation Error", "All fields must be filled in.")
            return False

        try:
            int(age)  # Check if age is a valid integer
        except ValueError:
            messagebox.showerror("Validation Error", "Age must be a valid integer.")
            return False

        try:
            float(salary_rate)  # Check if salary rate is a valid float
        except ValueError:
            messagebox.showerror("Validation Error", "Salary rate must be a valid number.")
            return False

        if not re.match(r'^\d{10}$', contact_number):
            # If you get questions about this say it is needed as it validates the contact number
            # and make sure that the input is a 10-digit number
            messagebox.showerror("Validation Error", "Contact number must be a 10-digit number.")
            return False

        # Additional validation checks can be added as needed

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

            proceed = messagebox.askyesno("Add Employee", "Do you want to add the employee?")

            if not proceed:
                return

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


def get_current_month():
    return datetime.now().strftime("%B")


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
        self.date_label = tk.Label(self, text="Date: ")

        # months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
        #           "November", "December"]
        #
        # self.date_field = ttk.Combobox(self, values=months)
        self.date_field = DateEntry(self, width=12, background='darkblue', foreground='white', borderwidth=2)

        self.hours_label = tk.Label(self, text="Hours Worked: ")
        self.hours_worked_field = tk.Entry(self)
        self.save_button = tk.Button(self, text="Update", command=self.update_employee)
        self.back_button = tk.Button(self, text="Back", command=self.go_to_dashboard)
        self.add_salary_button = tk.Button(self, text="Add Salary", command=self.add_salary)
        self.employee_id = None

        label_texts = ["ID: ", "Employee Number: ", "Employee Name: ", "Age: ", "Rank: ", "Salary Rate: ",
                       "Contact Number: "]

        for i, label_text in enumerate(label_texts):
            tk.Label(self, text=label_text).grid(row=i, column=0, sticky='e')

        self.id_label.grid(row=0, column=1)
        self.number_field.grid(row=1, column=1)
        self.name_field.grid(row=2, column=1)
        self.age_field.grid(row=3, column=1)
        self.rank_field.grid(row=4, column=1)
        self.salary_rate_field.grid(row=5, column=1)
        self.contact_number_field.grid(row=6, column=1)
        self.date_label.grid(row=9, column=2, sticky='e')
        self.date_field.grid(row=9, column=3, sticky='w')
        self.hours_label.grid(row=9, column=4, sticky='e')
        self.hours_worked_field.grid(row=9, column=5, sticky='w')
        self.back_button.grid(row=9, column=0, sticky='w', padx=20, pady=10)
        self.save_button.grid(row=9, column=1, sticky='e', padx=20, pady=10)
        self.add_salary_button.grid(row=9, column=6, padx=20, pady=10)

        columns = ("date", "rendered_hours", "gross_salary")
        self.table = ttk.Treeview(self, columns=columns, show='headings')
        self.table.heading("date", text="Date")
        self.table.heading("rendered_hours", text="Rendered Hours")
        self.table.heading("gross_salary", text="Gross Salary")
        self.table.grid(row=0, column=2, rowspan=9, columnspan=5, padx=20, pady=10)

    def load_employee_data(self, employee_id):
        self.clear_fields()
        self.id_label.config(text=employee_id)
        self.employee_id = employee_id

        db_conn = database_handler.DBHandler()
        employee_data = db_conn.read_one_employee(employee_id)
        db_conn.close()

        self.number_field.insert(0, employee_data.employee_number)
        self.name_field.insert(0, employee_data.employee_name)
        self.age_field.insert(0, employee_data.age)
        self.rank_field.insert(0, employee_data.rank)
        self.salary_rate_field.insert(0, employee_data.salary_rate)
        self.contact_number_field.insert(0, employee_data.contact_number)

        self.table.delete(*self.table.get_children())

        for payroll in employee_data.payrolls:
            self.table.insert("", "end", values=(payroll.date, payroll.rendered_hours, payroll.salary))

    def clear_fields(self):
        self.name_field.delete(0, tk.END)
        self.number_field.delete(0, tk.END)
        self.age_field.delete(0, tk.END)
        self.rank_field.delete(0, tk.END)
        self.salary_rate_field.delete(0, tk.END)
        self.contact_number_field.delete(0, tk.END)

    def validate_payroll(self):

        date = self.date_field.get()
        hours_worked = self.hours_worked_field.get()

        if not date or not hours_worked:
            messagebox.showerror("Add Salary", "Please fill up all fields")
            return False

        # Edit this if you edited the date_field
        months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
         "November", "December"]

        if date not in months:
            messagebox.showerror("Add Salary", "Invalid month")
            return False
        # --------------------------------------------

        try:
            int(hours_worked)
            return True
        except ValueError as e:
            print(e)
            messagebox.showerror("Add Salary", "Rendered Hours must be numbers")
            return False

    def add_salary(self):
        if not self.validate_payroll():
            return

        try:
            payroll = models.Payroll()
            payroll.employee_id = self.employee_id
            payroll.date = f'{self.date_field.get()} {datetime.now().year}'
            payroll.rendered_hours = int(self.hours_worked_field.get())
            payroll.salary = float(self.hours_worked_field.get()) * float(self.salary_rate_field.get())

            db_conn = database_handler.DBHandler()
            db_conn.add_payroll(payroll)
            db_conn.close()

            self.load_employee_data(self.employee_id)

            self.date_field.delete(0, tk.END)
            self.hours_worked_field.delete(0, tk.END)

            messagebox.showinfo("Add Salary", "Salary added successfully")
        except ValueError as e:
            print(e)
            messagebox.showerror("Add Salary", "Rendered Hours and Gross Salary must be numbers")

    def validate_data(self):
        employee_number = self.number_field.get()
        employee_name = self.name_field.get()
        age = self.age_field.get()
        rank = self.rank_field.get()
        salary_rate = self.salary_rate_field.get()
        contact_number = self.contact_number_field.get()

        # Perform validation checks
        if not employee_number or not employee_name or not age or not rank or not salary_rate or not contact_number:
            messagebox.showerror("Validation Error", "All fields must be filled in.")
            return False

        try:
            int(age)  # Check if age is a valid integer
        except ValueError:
            messagebox.showerror("Validation Error", "Age must be a valid integer.")
            return False

        try:
            float(salary_rate)  # Check if salary rate is a valid float
        except ValueError:
            messagebox.showerror("Validation Error", "Salary rate must be a valid number.")
            return False

        if not re.match(r'^\d{10}$', contact_number):
            # If you get questions about this say it is needed as it validates the contact number
            # and make sure that the input is a 10-digit number
            messagebox.showerror("Validation Error", "Contact number must be a 10-digit number.")
            return False

        return True

    def update_employee(self):
        if not self.validate_data():
            return

        try:
            new_employee = models.Employee()
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

    def on_return(self, employee_id=None):
        self.load_employee_data(employee_id)
