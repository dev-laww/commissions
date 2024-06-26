import logging
import os
import time
import tkinter as tk
from tkinter import messagebox

from PIL import ImageTk, Image

from utils import Constants, generate_suggestions


class SearchEntry:
    def __init__(self, root: tk.Tk):
        self.logger = logging.getLogger(__name__)
        self.logger.setLevel(logging.INFO)
        self.logger.addHandler(Constants.FILE_HANDLER)
        self.logger.addHandler(Constants.STREAM_HANDLER)

        self.logger.info('Initializing Search Entry Page')

        self.root = root

        self.root.title('Search Entry')
        self.root.geometry('600x400')
        self.root.resizable(False, False)

        self.search_entry = tk.Entry(self.root, font=('Helvetica', 20), fg='gray')
        self.search_entry.pack(pady=40)

        default_text = 'Search For Entries'
        self.search_entry.insert(0, default_text)
        self.search_entry.bind('<FocusIn>', self.on_entry_click)
        self.search_entry.bind('<FocusOut>', self.on_entry_leave)

        self.result_label = tk.Label(self.root, text='Results: ')
        self.result_label.pack()

        self.suggestions_box = tk.Listbox(self.root, width=50)
        self.suggestions_box.pack()

        self.log_book = tk.Button(self.root, text='View Log Book', command=self.see_log_book)
        self.log_book.pack()

        self.search_entry.bind('<KeyRelease>', self.check)

        self.suggestions_box.bind('<<ListboxSelect>>', self.__fillout)

        self.logger.info('Search Entry Page Initialized')

    def check(self, event):
        time.sleep(0.1)
        typed = self.search_entry.get()

        if typed == '':
            data = generate_suggestions(Constants.ENTRIES_PATH)
        else:
            data = []
            for item in generate_suggestions(Constants.ENTRIES_PATH):
                if typed.lower() in item.lower():
                    data.append(item)

        self.__update(data)

        if not typed:
            return
        if self.suggestions_box.size() == 0:
            messagebox.showinfo('No Results', 'No matching results found.')

    def __fillout(self, e):
        self.search_entry.delete(0, tk.END)
        self.search_entry.insert(0, self.suggestions_box.get(tk.ANCHOR))

        file_name = self.suggestions_box.get(tk.ANCHOR).lower().replace(' ', '-')

        if os.path.isfile(f'{Constants.ENTRIES_PATH}/{file_name}.txt'):
            with open(f'{Constants.ENTRIES_PATH}/{file_name}.txt', 'r') as file:
                file_content = file.read()
                self.display_file_content(
                    file_content,
                    file_name
                )

    def __update(self, data):
        self.suggestions_box.delete(0, tk.END)

        for item in data:
            self.suggestions_box.insert(tk.END, item)

    def display_file_content(self, content, file_name):
        window = tk.Toplevel(self.root)

        window.geometry('400x400')

        text_widget = tk.Text(window, height=10, width=50)
        text_widget.pack(pady=(10, 0), padx=10)

        text_widget.insert(tk.END, content)

        with open(f'{Constants.QR_CODES_PATH}/{file_name}.png', 'rb') as file:
            img = Image.open(file)
            img = img.resize((200, 200))
            img = ImageTk.PhotoImage(img)

            qr_code_label = tk.Label(window, image=img)
            qr_code_label.image = img
            qr_code_label.pack(pady=10, padx=10)

    def on_entry_click(self, event):
        if self.search_entry.get() == 'Search For Entries':
            self.search_entry.delete(0, tk.END)
            self.search_entry.configure(foreground='black')

    def on_entry_leave(self, event):
        if self.search_entry.get() == '':
            self.search_entry.insert(0, 'Search For Entries')
            self.search_entry.configure(foreground='gray')

    def see_log_book(self):
        self.logger.info('Viewing Log Book')

        log_window = tk.Toplevel(self.root)
        log_window.title('Log Book')
        log_window.geometry('400x300')

        text_widget = tk.Text(log_window, height=10, width=50)
        text_widget.pack(pady=10)

        try:
            with open(Constants.LOG_PATH, 'r') as log_file:
                content = log_file.read()
                text_widget.insert(tk.END, content)
        except FileNotFoundError:
            text_widget.insert(tk.END, 'Log Book is Empty.')
