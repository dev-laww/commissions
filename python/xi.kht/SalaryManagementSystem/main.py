import tkinter as tk

import pages


class MainWindow(tk.Tk):
    def __init__(self):
        tk.Tk.__init__(self)
        self.title("Salary Management System")
        self.frames = dict()
        self.frames['Dashboard'] = pages.Dashboard(self)
        self.frames['Add_Employee'] = pages.Add_Form(self)
        self.frames['Update_Employee'] = pages.Update_Form(self)

        self.change_window('Dashboard')

    def change_window(self, name, **kwargs):
        for frame in self.frames.values():
            frame.grid_forget()

        self.frames[name].on_return(**kwargs)
        self.frames[name].grid(row=0, column=0)


root = MainWindow()
root.mainloop()
