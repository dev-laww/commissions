import tkinter as tk

import requests


class CurrencyConverter:
    def __init__(self, master):
        self.master = master
        self.master.title("Currency Converter")

        self.currencies = {'USD': 1, 'EUR': 0.92}
        self.from_currency_var = tk.StringVar(value="USD")
        self.to_currency_var = tk.StringVar(value="EUR")
        self.amount_var = tk.StringVar()
        self.converted_amount_var = tk.StringVar()

        self._create_widgets()

    def _create_widgets(self):
        # Create the input widgets
        tk.Label(
            self.master,
            text="From Currency:"
        ).grid(row=0, column=0, padx=10, pady=10)
        tk.OptionMenu(
            self.master,
            self.from_currency_var,
            *self.currencies.keys()
        ).grid(row=0, column=1, padx=10, pady=10)

        tk.Label(
            self.master,
            text="To Currency:"
        ).grid(row=1, column=0, padx=10, pady=10)
        tk.OptionMenu(
            self.master,
            self.to_currency_var,
            *self.currencies.keys()
        ).grid(row=1, column=1, padx=10, pady=10)

        tk.Label(
            self.master,
            text="Amount:"
        ).grid(row=2, column=0, padx=10, pady=10)
        tk.Entry(
            self.master,
            textvariable=self.amount_var
        ).grid(row=2, column=1, padx=10, pady=10)

        # Create the action widgets
        tk.Button(
            self.master,
            text="Convert",
            command=self.convert
        ).grid(row=3, column=1, padx=10, pady=10)
        tk.Label(
            self.master,
            text="Converted Amount:"
        ).grid(row=4, column=0, padx=10, pady=10)
        tk.Label(
            self.master,
            textvariable=self.converted_amount_var
        ).grid(row=4, column=1, padx=10, pady=10)

        # Create the "Swap" button
        tk.Button(
            self.master,
            text="Swap",
            command=self.swap_currencies
        ).grid(row=0, column=2, rowspan=2, padx=10, pady=10)

    def convert(self):
        from_currency = self.from_currency_var.get()
        to_currency = self.to_currency_var.get()
        amount = float(self.amount_var.get())
        rate = self.currencies[to_currency] / self.currencies[from_currency]
        converted_amount = round(amount * rate, 2)
        self.converted_amount_var.set(converted_amount)

    def swap_currencies(self):
        from_currency = self.from_currency_var.get()
        to_currency = self.to_currency_var.get()
        self.from_currency_var.set(to_currency)
        self.to_currency_var.set(from_currency)


root = tk.Tk()
app = CurrencyConverter(root)
root.mainloop()
