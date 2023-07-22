import tkinter as tk


class SearchEntry(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contact Tracing")
        self.geometry("600x400")
        self.resizable(False, False)
