import tkinter as tk


class CurrencyConverter:
    def __init__(self, master):
        self.master = master
        self.master.title("Currency Converter")
        self.currencies = {"USD": 1, "EUR": 0.84, "JPY": 108.88, "GBP": 0.72, "AUD": 1.31, "CAD": 1.25}
        self.from_currency_var = tk.StringVar(value="USD")
        self.to_currency_var = tk.StringVar(value="EUR")
        self.amount_var = tk.StringVar()
        self.converted_amount_var = tk.StringVar()

        # Create widgets
        tk.Label(self.master, text="From Currency:").grid(row=0, column=0)
        tk.OptionMenu(self.master, self.from_currency_var, *self.currencies.keys()).grid(row=0, column=1)
        tk.Label(self.master, text="To Currency:").grid(row=1, column=0)
        tk.OptionMenu(self.master, self.to_currency_var, *self.currencies.keys()).grid(row=1, column=1)
        tk.Label(self.master, text="Amount:").grid(row=2, column=0)
        tk.Entry(self.master, textvariable=self.amount_var).grid(row=2, column=1)
        tk.Button(self.master, text="Convert", command=self.convert).grid(row=3, column=1)
        tk.Label(self.master, text="Converted Amount:").grid(row=4, column=0)
        tk.Label(self.master, textvariable=self.converted_amount_var).grid(row=4, column=1)

    def convert(self):
        from_currency = self.from_currency_var.get()
        to_currency = self.to_currency_var.get()
        amount = float(self.amount_var.get())
        rate = self.currencies[from_currency] / self.currencies[to_currency]
        converted_amount = round(amount * rate, 2)
        self.converted_amount_var.set(converted_amount)


root = tk.Tk()
app = CurrencyConverter(root)
root.mainloop()
