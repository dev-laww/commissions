import tkinter as tk

import pages


class MainWindow(tk.Tk):
    def __init__(self):
        tk.Tk.__init__(self)
        self.frames = dict()
        self.frames["Dashboard"] = pages.DashBoard(self)
        self.frames["Add_Employee"] = pages.EmployeeForm(self)
        self.frames["Delete_Employee"] = pages.DeleteEmployee(self)

        self.change_frame("Dashboard")

    def change_frame(self, name):
        for frame in self.frames.values():
            frame.grid_forget()
        self.frames[name].on_return()
        self.frames[name].grid(row=0, column=0)


root = MainWindow()
root.mainloop()
