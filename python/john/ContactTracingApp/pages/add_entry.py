import logging
import tkinter as tk
from datetime import datetime
from textwrap import dedent
from tkinter import messagebox

import qrcode

from utils import generate_suggestions, Constants


class AddEntry:
    def __init__(self, root: tk.Tk):
        self.logger = logging.getLogger(__name__)
        self.logger.setLevel(logging.INFO)
        self.logger.addHandler(Constants.FILE_HANDLER)
        self.logger.addHandler(Constants.STREAM_HANDLER)

        self.logger.info('Initializing Add Entry Page')

        self.root = root

        self.root.title('Contact Tracing')
        self.root.geometry('1000x600')
        self.root.resizable(False, False)

        self.selected_items = []
        self.selected_items_2 = []
        self.selected_option = tk.IntVar()
        self.selected_gender = tk.IntVar()
        self.confirmed_cases = []

        self.title_label = tk.Label(self.root, text='Please Fill Out The Following Information: ', font=('Arial', 16))
        self.title_label.place(x=300, y=50)

        self.demographic_label = tk.Label(self.root, text='Basic Demographic')
        self.demographic_label.place(x=100, y=90)

        self.name_label = tk.Label(self.root, text='Full Name:')
        self.name_label.place(x=100, y=140)
        self.name_entry = tk.Entry(self.root)
        self.name_entry.place(x=100, y=160)

        self.age_label = tk.Label(self.root, text='Age:')
        self.age_label.place(x=100, y=220)
        self.age_entry = tk.Entry(self.root)
        self.age_entry.place(x=100, y=240)

        self.age_entry.bind('<KeyRelease>', self.validate_age_entry)

        self.gender_label = tk.Label(self.root, text='Gender:')
        self.gender_label.place(x=100, y=300)

        female_radio = tk.Radiobutton(self.root, text='Female', variable=self.selected_gender, value='1')
        female_radio.place(x=100, y=320)

        male_radio = tk.Radiobutton(self.root, text='Male', variable=self.selected_gender, value='2')
        male_radio.place(x=100, y=340)

        prefer_not_radio = tk.Radiobutton(self.root, text='Prefer Not To Say', variable=self.selected_gender, value='3')
        prefer_not_radio.place(x=100, y=360)

        self.address_label = tk.Label(self.root, text='Adress:')
        self.address_label.place(x=100, y=420)
        self.address_entry = tk.Entry(self.root)
        self.address_entry.place(x=100, y=440)

        self.contact_label = tk.Label(self.root, text='Contact Details:')
        self.contact_label.place(x=100, y=500)
        self.contact_entry = tk.Entry(self.root)
        self.contact_entry.place(x=100, y=520)

        self.test_label = tk.Label(self.root, text='What Locations Have you been to the last 14 days?:')
        self.test_label.place(x=300, y=90)

        self.search_location = tk.Entry(self.root, font=('Helvetica', 16), fg='gray')
        self.search_location.place(x=300, y=110)

        default_text = 'Search For Locations'
        self.search_location.insert(0, default_text)
        self.search_location.bind('<FocusIn>', self.on_entry_click)
        self.search_location.bind('<FocusOut>', self.on_entry_leave)

        self.suggestions_box_1_results = tk.Label(self.root, text='Results:')
        self.suggestions_box_1_results.place(x=300, y=140)

        self.suggestions_box_1 = tk.Listbox(self.root, width=50, selectmode=tk.MULTIPLE)
        self.suggestions_box_1.place(x=300, y=160)

        self.suggestions_box_2_results = tk.Label(self.root, text='Selected Location:')
        self.suggestions_box_2_results.place(x=300, y=330)

        self.suggestions_box_2 = tk.Listbox(self.root, width=50, selectmode=tk.MULTIPLE)
        self.suggestions_box_2.place(x=300, y=350)

        self.search_location.bind('<KeyRelease>', self.check)

        self.suggestions_box_1.bind('<<ListboxSelect>>', self.transfer_items)
        self.suggestions_box_2.bind('<<ListboxSelect>>', self.transfer_items)

        self.test_label = tk.Label(self.root, text='Add New Location:')
        self.test_label.place(x=650, y=90)
        self.new_choice_entry = tk.Entry(self.root)
        self.new_choice_entry.place(x=650, y=110)

        self.new_choice_button = tk.Button(self.root, text='Add New Location', command=self.add_new_choice)
        self.new_choice_button.place(x=650, y=140)

        self.test_label = tk.Label(self.root, text='Have you been tested for the last 14 days?:')
        self.test_label.place(x=650, y=220)

        yes_negative_radio = tk.Radiobutton(self.root, text='Yes-Negative', variable=self.selected_option, value='1')
        yes_negative_radio.place(x=650, y=240)

        yes_positive_radio = tk.Radiobutton(self.root, text='Yes-Positive', variable=self.selected_option, value='2')
        yes_positive_radio.place(x=650, y=260)

        no_radio = tk.Radiobutton(self.root, text='No', variable=self.selected_option, value='3')
        no_radio.place(x=650, y=280)

        button = tk.Button(self.root, text='Submit', command=self.export_input)
        button.place(x=500, y=550)

        self.logger.info('Add Entry Page Initialized')

    def check(self, e):
        typed = self.search_location.get().lower()
        selected_indices_1 = self.suggestions_box_1.curselection()
        self.suggestions_box_1.delete(0, tk.END)

        if typed:
            for item in generate_suggestions(Constants.LOCATIONS_PATH):
                if typed not in item.lower() or item in self.suggestions_box_2.get(0, tk.END):
                    continue

                self.suggestions_box_1.insert(tk.END, item)

        for index in selected_indices_1:
            self.suggestions_box_1.select_set(index)

        if not typed:
            return

        if self.suggestions_box_1.size() == 0:
            messagebox.showinfo('No Results', 'No matching results found. Please Add A New Location')

    def transfer_items(self, event):
        selected_indices_1 = self.suggestions_box_1.curselection()

        for index in selected_indices_1:
            item = self.suggestions_box_1.get(index)

            if item in self.suggestions_box_2.get(0, tk.END):
                continue

            self.selected_items_2 = list(self.suggestions_box_2.get(0, tk.END))
            self.selected_items_2.append(item)
            self.suggestions_box_2.insert(tk.END, item)

        selected_indices_2 = self.suggestions_box_2.curselection()
        for index in selected_indices_2:
            item = self.suggestions_box_2.get(index)

            answer = messagebox.askyesno('Remove Location', f'Are you sure you want to remove {item}?')
            if not answer:
                continue

            self.suggestions_box_2.delete(index)

    def add_new_choice(self):
        new_choice = self.new_choice_entry.get()

        if ',' in new_choice:
            self.logger.warning(f'Invalid location input: {new_choice}')
            messagebox.showerror('Invalid Input', 'Commas are not allowed in the location name.')
            return

        with open(Constants.LOCATIONS_PATH, 'a') as file:
            file.write(f'\n{new_choice}')

        messagebox.showinfo('New Location Added', 'The location has been added successfully.')
        self.logger.info(f'Added new location: {new_choice}')

    def on_entry_click(self, event):
        if self.search_location.get() != 'Search For Locations':
            return

        self.search_location.delete(0, tk.END)
        self.search_location.configure(foreground='black')

    def on_entry_leave(self, event):
        if self.search_location.get():
            return

        self.search_location.insert(0, 'Search For Locations')
        self.search_location.configure(foreground='gray')

    def validate_age_entry(self, event):
        age = self.age_entry.get()

        if not age:
            return

        try:
            int(age)
        except ValueError:
            self.logger.warning(f'Invalid age input: {age}')
            messagebox.showerror('Invalid Input', 'Age must be an Integer')
            self.age_entry.delete(0, tk.END)

    def export_input(self):
        name = self.name_entry.get()
        get_age = self.age_entry.get()

        try:
            get_age = int(get_age)
            if get_age <= 0 or get_age >= 100:
                raise ValueError
            else:
                age = get_age
        except ValueError:
            messagebox.showerror('Invalid Age', 'Please enter a valid age (1-99).')
            return

        gender_selected = self.selected_gender.get()

        gender_map = {
            1: 'Female',
            2: 'Male',
            3: 'Prefer Not To Say'
        }

        gender = gender_map.get(gender_selected, 'Prefer Not To Say')
        address = self.address_entry.get()
        contact = self.contact_entry.get()

        self.selected_items = self.suggestions_box_2.get(0, tk.END)
        location = ', '.join(self.selected_items)
        selected_option = self.selected_option.get()

        test_map = {
            1: 'Yes-Negative',
            2: 'Yes-Positive',
            3: 'Not Tested'
        }

        test_result = test_map.get(selected_option, 'Not Tested')

        field_map = {
            'Name': name,
            'Age': age,
            'Gender': gender,
            'Address': address,
            'Contact Details': contact,
            'Tested for Covid-19 the last 14 days': test_result,
            'Locations Visited Last 14 Days': location
        }

        missing_fields = []
        for field, value in field_map.items():
            if not value:
                missing_fields.append(field)

        if missing_fields:
            messagebox.showerror('Missing Fields', f'Please fill out the following fields: {", ".join(missing_fields)}')
            self.logger.warning(f'Missing fields: {", ".join(missing_fields)}')
            return

        timestamp = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        content = dedent(f'''
            Timestamp: {timestamp}
            Name: {name}
            Age: {age}
            Gender: {gender}
            Address: {address}
            Contact Details: {contact}
            Tested for Covid-19 the last 14 days: {test_result}
            Locations Visited Last 14 Days: {location}
        ''')

        if test_result == 'Yes-Positive':
            self.add_to_confirmed_cases(name, location)

        try:
            file_name = f'{name.replace(" ", "-").lower()}'
            with open(f'{Constants.ENTRIES_PATH}/{file_name}.txt', 'w') as file:
                file.write(content)

            qr_code = qrcode.make(content)
            qr_code.save(f'{Constants.QR_CODES_PATH}/{file_name}.png')

            messagebox.showinfo('Export Successful', f'Content exported')
            self.logger.info(f'Exported entry for {name}.')

        except Exception as e:
            self.logger.error(f'Export failed: {e}')
            messagebox.showerror('Export Failed', str(e))
            return

        confirm_contact = messagebox.askyesno(
            'Contact Tracing',
            'Do you want to know your possible contacts with confirmed cases?'
        )

        if confirm_contact:
            self.show_possible_contacts()

        self.clear()
        self.logger.info(f'Exported entry for {name}.')

    def show_possible_contacts(self):
        visited_locations = self.suggestions_box_2.get(0, tk.END)

        try:
            with open(Constants.CONFIRMED_CASES_PATH, 'r') as file:
                confirmed_cases = file.read().splitlines()
        except FileNotFoundError:
            messagebox.showinfo('No Confirmed Cases', 'There are no confirmed cases yet.')
            return

        word_counts = {location: 0 for location in visited_locations}

        current_time = datetime.now()

        for case in confirmed_cases:
            name, locations, timestamp_str = case.split('\t')
            locations = locations.split(', ')
            timestamp = datetime.strptime(timestamp_str, '%Y-%m-%d %H:%M:%S')

            time_difference = current_time - timestamp

            if time_difference.days > 14:
                continue

            if name == self.name_entry.get() and self.selected_option.get() == 2:
                continue

            for location in locations:
                if location in word_counts:
                    word_counts[location] += 1

        contacts_window = tk.Toplevel(self.root)
        contacts_window.title('Possible Contacts with Confirmed Cases in the Last 14 Days')
        contacts_window.geometry('400x300')

        for location in visited_locations:
            num_cases = word_counts[location]
            label_text = dedent(f'''
                Location: {location}
                Number of Confirmed Cases: {num_cases}
            ''')
            location_label = tk.Label(contacts_window, text=label_text)
            location_label.pack()

    def add_to_confirmed_cases(self, name, location):
        timestamp = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

        self.confirmed_cases.append((name, location, timestamp))
        with open(Constants.CONFIRMED_CASES_PATH, 'a') as file:
            file.write(f'{name}\t{location}\t{timestamp}\n')

    def clear(self):
        self.name_entry.delete(0, tk.END)
        self.age_entry.delete(0, tk.END)
        self.address_entry.delete(0, tk.END)
        self.contact_entry.delete(0, tk.END)
        self.search_location.delete(0, tk.END)
        self.search_location.insert(0, 'Search For Locations')
        self.new_choice_entry.delete(0, tk.END)

        self.selected_gender.set(0)
        self.selected_option.set(0)

        self.suggestions_box_2.delete(0, tk.END)

        self.selected_items = []
        self.selected_items_2 = []
