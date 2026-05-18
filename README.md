# Console-Input
This respository is holding the program. I am sick of having to always redo input, so I just want a goto console input. 
ConsoleInput - README
=====================

Overview
--------
ConsoleInput is a Java utility class that provides safe, validated, and user-friendly
console input handling. It wraps around Java's Scanner class and adds features such as:

- Integer input (with or without confirmation)
- Double input (with or without confirmation)
- Boolean input with multiple accepted formats
- String input (with or without confirmation)
- Range validation for numeric input
- Clear and consistent error messages
- Automatic handling of Scanner newline issues
- Protection against reading from a closed Scanner

This class is designed for console-based applications that require reliable and
user-friendly input handling.

Key Features
------------
1. Integer Input
   - nextIntNoCon(description)
   - nextIntNoCon(description, min, max)
   - nextIntCon(description)
   - nextIntCon(description, min, max)

2. Double Input
   - nextDoubleNoCon(description)
   - nextDoubleNoCon(description, min, max)
   - nextDoubleCon(description)
   - nextDoubleCon(description, min, max)

3. Boolean Input
   - nextBoolean(description)
   Accepts: y, yes, 1, true, t, n, no, 2, false, f

4. String Input
   - nextLineNoCon(description)
   - nextLineCon(description)

5. Safety and Utility
   - ensureOpen() prevents reading from a closed Scanner
   - closed() safely closes the Scanner
   - All methods properly consume leftover newline characters

Example Usage
-------------
Scanner sc = new Scanner(System.in);
ConsoleInput input = new ConsoleInput(sc);

int age = input.nextIntCon("Enter your age", 0, 150);
double price = input.nextDoubleNoCon("Enter the price");
boolean confirm = input.nextBoolean("Do you want to continue");
String name = input.nextLineCon("Enter your full name");

input.closed();

Why Use ConsoleInput?
---------------------
Java's Scanner class is powerful but can be error-prone when handling user input.
Common issues include:

- nextInt() leaving behind newline characters
- Invalid input causing infinite loops or crashes
- Limited boolean parsing
- Repetitive code for validation and confirmation
- Manual range checking

ConsoleInput solves these problems by providing:

- Clean, reusable input methods
- Built-in validation and confirmation
- Consistent prompts and error messages
- Safe Scanner handling

Requirements
-----------
- Java 8 or later
- A Scanner instance passed into the constructor

License
-------
This class may be used, modified, or included in academic or personal projects.
