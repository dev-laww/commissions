import tkinter as tk


class OnScreenKeyboard:
    def __init__(self, master):
        self.master = master
        self.master.title("On-Screen Keyboard")
        self.master.resizable(False, False)

        # create a text entry field
        self.text_entry = tk.Entry(self.master, width=30)
        self.text_entry.grid(row=0, column=0, columnspan=20, padx=10, pady=10)
        self.caps = False

        # create buttons for each key on the keyboard
        # try to find a way to minimize the usage of if statements
        # mas maganda kung may way na hindi na kailangan ng if statements
        # pasensya na makalat T^T

        buttons = [
            "`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "<-",
            "Tab", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
            "Caps", "a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'", "Enter",
            "Shift", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "▲",
            "Ctrl", "Win", "Alt", " ", "Alt", "Ctrl", "◄", "▼", "►", "Ins", "Home", "PgUp", "Delete", "End", "PgDn"
        ]

        # create and grid each button
        self.buttons = {}
        for i, key in enumerate(buttons):
            if key == " ":
                self.buttons[key] = tk.Button(self.master, text=" ", width=20, state=tk.DISABLED)
                self.buttons[key].grid(row=4, column=1, columnspan=8)

            elif key == "<-":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="<-",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=1, column=14)

            elif key == "Enter":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="Enter",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=2, column=13)
            elif key == "Tab":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="Tab",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=1, column=0)
            elif key == "Caps":
                self.buttons[key] = tk.Button(
                    self.master, text="Caps",
                    width=5,
                    command=self.toggle_caps
                )
                self.buttons[key].grid(row=2, column=0)
            elif key == "Shift":
                self.buttons[key] = tk.Button(
                    self.master, text="Shift",
                    width=5,
                    command=self.toggle_shift
                )
                self.buttons[key].grid(row=3, column=0)
            elif key == "Ctrl":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="Ctrl",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=4, column=0)
            elif key == "Win":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="Win",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=4, column=1)
            elif key == "Alt":
                self.buttons[key] = tk.Button(
                    self.master,
                    text=key,
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=4, column=2)

            elif key == "▲":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="▲",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=3, column=12)

            elif key == "◄":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="◄",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=4, column=13)
            elif key == "▼":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="▼",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=4, column=12)
            elif key == "►":
                self.buttons[key] = tk.Button(
                    self.master,
                    text="►",
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
                self.buttons[key].grid(row=4, column=14)
            else:
                self.buttons[key] = tk.Button(
                    self.master,
                    text=key,
                    width=5,
                    command=lambda k=key: self.handle_key_press(k)
                )
            if i < 14:
                self.buttons[key].grid(row=1, column=i)
                continue

            if 14 <= i < 28:
                self.buttons[key].grid(row=2, column=i - 14)
                continue

            if 28 <= i < 41:
                self.buttons[key].grid(row=3, column=i - 28)
                continue

            self.buttons[key].grid(row=5, column=i - 41)

        # initialize state variables
        self.caps_lock = False
        self.shift = False

        # bind keys to event handlers
        self.master.bind("<KeyPress>", self.handle_key_press_event)
        self.master.bind("<KeyRelease>", self.handle_key_release_event)

    def handle_key_press_event(self, event):
        """Handle a key press event."""
        key = event.keysym
        if key == "Caps_Lock":
            self.toggle_caps()
        elif key == "Shift_L" or key == "Shift_R":
            self.toggle_shift()
        elif key == "Return":
            key = "Enter"
        elif key == "Tab":
            pass
        elif key == "ISO_Left_Tab":
            key = "Tab"
        elif key == "space":
            key = " "
        elif key == "Up":
            key = "▲"
        elif key == "Down":
            key = "▼"
        elif key == "Left":
            key = "◄"
        elif key == "Right":
            key = "►"
        elif key == "Delete":
            key = "Delete"
        elif key == "Home":
            key = "Home"
        elif key == "End":
            key = "End"
        elif key == "Insert":
            key = "Ins"
        elif key == "Prior":
            key = "PgUp"
        elif key == "Next":
            key = "PgDn"
        elif key == "Menu":
            key = "Win"
        elif len(key) == 1:
            if self.caps_lock or self.shift:
                key = key.upper()
            else:
                key = key.lower()
        if key in self.buttons:
            self.buttons[key].invoke()

    def handle_key_release_event(self, event):
        """Handle a key release event."""
        key = event.keysym
        if key == "Shift_L" or key == "Shift_R":
            self.toggle_shift()

    def handle_key_press(self, key):
        """Handle a key press."""

        # kayo na din bahala sa win, alt, ctrl, tab, enter, space, bksp, del, home, end, pgup, pgdn

        if key == "Caps":
            self.toggle_caps()
        elif key == "Shift":
            self.toggle_shift()
        elif key == "Win":
            pass
        elif key == "Alt":
            pass
        elif key == "Ctrl":
            pass
        elif key == "Tab":
            pass
        elif key == "Enter":
            self.text_entry.insert(tk.END, "\n")
        elif key == "Space":
            self.text_entry.insert(tk.END, " ")
        elif key == "Bksp":
            self.text_entry.delete("end-2c", tk.END)
        elif key == "Del":
            self.text_entry.delete(tk.END)
        elif key == "Home":
            pass
        elif key == "End":
            pass
        elif key == "PgUp":
            pass
        elif key == "PgDn":
            pass
        else:
            self.text_entry.insert(tk.END, key)

    def toggle_caps(self):
        """Toggle the state of the caps lock."""
        self.caps_lock = not self.caps_lock
        if self.caps_lock:
            for key in self.buttons:
                if len(key) == 1:
                    self.buttons[key]["text"] = key.upper()
        else:
            for key in self.buttons:
                if len(key) == 1:
                    self.buttons[key]["text"] = key.lower()

    def toggle_shift(self):
        """Toggle the state of the shift key."""
        self.shift = not self.shift
        for key in self.buttons:
            if len(key) == 1:
                if self.shift:
                    self.buttons[key]["text"] = key.upper()
                else:
                    self.buttons[key]["text"] = key.lower()

    def run(self):
        """Run the application."""
        self.master.mainloop()


if __name__ == "__main__":
    root = tk.Tk()
    app = OnScreenKeyboard(root)
    app.run()
