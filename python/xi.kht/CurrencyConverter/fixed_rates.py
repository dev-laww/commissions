import tkinter as tk
from tkinter import ttk


class CurrencyConverter:
    def __init__(self, master):
        self.master = master
        self.master.title("Currency Converter")

        self.currencies = {'USD': 1, 'EUR': 0.92}
        self.from_currency_var = tk.StringVar(value="USD")
        self.to_currency_var = tk.StringVar(value="EUR")
        self.amount_var = tk.StringVar()
        self.converted_amount_var = tk.StringVar()

        self._create_styles()
        self._create_widgets()

    def _create_styles(self):
        # Create the styles for the widgets
        self.style = ttk.Style()

        # Configure the labels
        self.style.configure("TLabel", padding=(10, 5))

        # Configure the option menus
        self.style.configure("TMenubutton", padding=(10, 5))

        # Configure the entry widgets
        self.style.configure("TEntry", padding=(10, 5))

        # Configure the buttons
        self.style.configure("TButton", padding=(10, 5))

    def _create_widgets(self):
        # Create the input widgets
        ttk.Label(
            self.master,
            text="From Currency:"
        ).grid(row=0, column=0)
        ttk.OptionMenu(
            self.master,
            self.from_currency_var,
            'USD',
            *['USD', 'EUR']
        ).grid(row=0, column=1, padx=10, pady=10)

        ttk.Label(
            self.master,
            text="To Currency:"
        ).grid(row=1, column=0, padx=10, pady=10)
        ttk.OptionMenu(
            self.master,
            self.to_currency_var,
            'EUR',
            *['USD', 'EUR'],
        ).grid(row=1, column=1, padx=10, pady=10)

        ttk.Label(
            self.master,
            text="Amount:"
        ).grid(row=2, column=0, padx=10, pady=10)
        ttk.Entry(
            self.master,
            textvariable=self.amount_var
        ).grid(row=2, column=1, padx=10, pady=10)

        # Create the action widgets
        ttk.Button(
            self.master,
            text="Convert",
            command=self.convert
        ).grid(row=3, column=1, padx=10, pady=10)
        ttk.Label(
            self.master,
            text="Converted Amount:"
        ).grid(row=4, column=0, padx=10, pady=10)
        ttk.Label(
            self.master,
            textvariable=self.converted_amount_var
        ).grid(row=4, column=1, padx=10, pady=10)

        # Create the "Swap" button
        ttk.Button(
            self.master,
            text="Swap",
            command=self.swap_currencies,
        ).grid(row=0, column=2, rowspan=2, padx=10, pady=10)

    def convert(self):
        # Get the input values
        from_currency = self.from_currency_var.get()
        to_currency = self.to_currency_var.get()
        amount = float(self.amount_var.get())

        # Convert the currency
        rate = self.currencies[to_currency] / self.currencies[from_currency]
        converted_amount = round(amount * rate, 2)

        # Update the converted amount label
        self.converted_amount_var.set(converted_amount)

    def swap_currencies(self):
        # Swap the "from" and "to" currencies
        from_currency = self.from_currency_var.get()
        to_currency = self.to_currency_var.get()
        self.from_currency_var.set(to_currency)
        self.to_currency_var.set(from_currency)


root = tk.Tk()
app = CurrencyConverter(root)
root.mainloop()
