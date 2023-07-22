import tkinter as tk

from pages import AddEntry, SearchEntry


class MainPage(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title('Contact Tracing')
        self.geometry('300x150')
        self.resizable(False, False)

        self.add_entry_button = tk.Button(self, text='Add Entry', command=self.__add_entry)
        self.add_entry_button.pack(pady=(30, 15))

        self.search_entry_button = tk.Button(self, text='Search Entry', command=self.__search_entry)
        self.search_entry_button.pack(pady=(15, 30))

    def __add_entry(self):
        for widget in self.winfo_children():
            widget.destroy()

        self.go_back = tk.Button(self, text='Return', command=self.__go_back)
        self.go_back.pack(pady=10)

        self.add_entry = AddEntry(self)

    def __search_entry(self):
        for widget in self.winfo_children():
            widget.destroy()

        self.go_back = tk.Button(self, text='Return', command=self.__go_back)
        self.go_back.pack(pady=10)

        self.search_entry = SearchEntry(self)

    def __go_back(self):
        for widget in self.winfo_children():
            widget.destroy()

        self.add_entry_button = tk.Button(self, text='Add Entry', command=self.__add_entry)
        self.add_entry_button.pack(pady=(30, 15))

        self.search_entry_button = tk.Button(self, text='Search Entry', command=self.__search_entry)
        self.search_entry_button.pack(pady=(15, 30))
        self.geometry('300x150')


if __name__ == "__main__":
    main = MainPage()
    main.mainloop()
