class Employee:
    def __init__(self):
        self.id = 0
        self.employee_number = ''
        self.employee_name = ''
        self.age = 0
        self.rank = ''
        self.salary_rate = 0.0
        self.contact_number = ''
        self.payrolls = []


class Payroll:
    def __init__(self):
        self.id = 0
        self.employee_id = 0
        self.date = ''
        self.rendered_hours = 0
        self.salary = 0.0