class Employee:
    def __init__(
            self,
            name: str,
            number: str,
            age: int,
            salary: float,
            contact: str,
            employee_id: int = 0
    ):
        self.id = employee_id,
        self.employee_name = name,
        self.employee_number = number,
        self.age = age,
        self.salary_rate = salary,
        self.contact_number = contact


class Salary:
    def __init__(
            self,
            employee_id: int,
            date: str,
            rendered_hours: int,
            gross_salary: float,
            salary_id: int = 0
    ):
        self.id = salary_id,
        self.employee_id = employee_id,
        self.date = date,
        self.rendered_hours = rendered_hours,
        self.gross_salary = gross_salary
