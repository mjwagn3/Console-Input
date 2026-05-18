import java.util.Scanner;

/**
 * A robust console input handler that supports validated and confirmed
 * input for integers, doubles, booleans, and strings. Ensures the Scanner
 * remains synchronized and prevents invalid input from breaking logic.
 */
public class ConsoleInput {

    private boolean closed;
    private Scanner io;

    /**
     * Creates a new ConsoleInput wrapper around a Scanner.
     *
     * @param io the Scanner to read from
     */
    public ConsoleInput(Scanner io) {
        this.io = io;
        this.closed = false;
    }

    /**
     * Closes the scanner if it is not already closed.
     *
     * @return true once the scanner is closed
     */
    public boolean closed() {
        if (!closed) {
            closed = true;
            io.close();
        }
        return closed;
    }

    /**
     * Ensures the scanner is still open.
     *
     * @throws IllegalStateException if the scanner has been closed
     */
    private void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("Scanner is closed.");
        }
    }

    // ============================================================
    // INTEGER INPUT — NO CONFIRMATION
    // ============================================================

    /**
     * Reads an integer from the user without confirmation.
     *
     * @param description prompt shown to the user
     * @return the validated integer
     */
    public int nextIntNoCon(String description) {
        ensureOpen();
        System.out.print(description + ": ");

        while (!io.hasNextInt()) {
            System.out.println("Invalid Response. Please enter an integer.");
            System.out.print(description + ": ");
            io.nextLine();
        }

        int num = io.nextInt();
        io.nextLine();
        return num;
    }

    /**
     * Reads an integer within a specified range without confirmation.
     *
     * @param description prompt shown to the user
     * @param min inclusive lower bound
     * @param max exclusive upper bound
     * @return validated integer within the range
     */
    public int nextIntNoCon(String description, int min, int max) {
        ensureOpen();
        if (max <= min) throw new IllegalArgumentException("max must be greater than min");

        int num;
        do {
            System.out.print(description + ": ");

            while (!io.hasNextInt()) {
                System.out.println("Invalid Response. Please enter an integer.");
                System.out.print(description + ": ");
                io.nextLine();
            }

            num = io.nextInt();
            io.nextLine();

            if (num < min || num >= max) {
                System.out.println("Please enter a value such that " + min + " <= num < " + max);
            }

        } while (num < min || num >= max);

        return num;
    }

    // ============================================================
    // INTEGER INPUT — WITH CONFIRMATION
    // ============================================================

    /**
     * Reads an integer with user confirmation.
     *
     * @param description prompt shown to the user
     * @return confirmed integer
     */
    public int nextIntCon(String description) {
        ensureOpen();
        while (true) {
            int num = nextIntNoCon(description);
            if (nextBoolean("You entered \"" + num + "\". Is this correct")) {
                return num;
            }
            System.out.println("Try again.");
        }
    }

    /**
     * Reads an integer within a range with user confirmation.
     *
     * @param description prompt shown to the user
     * @param min inclusive lower bound
     * @param max exclusive upper bound
     * @return confirmed integer within range
     */
    public int nextIntCon(String description, int min, int max) {
        ensureOpen();
        while (true) {
            int num = nextIntNoCon(description, min, max);
            if (nextBoolean("You entered \"" + num + "\". Is this correct")) {
                return num;
            }
            System.out.println("Try again.");
        }
    }

    // ============================================================
    // DOUBLE INPUT — NO CONFIRMATION
    // ============================================================

    /**
     * Reads a double from the user without confirmation.
     *
     * @param description prompt shown to the user
     * @return validated double
     */
    public double nextDoubleNoCon(String description) {
        ensureOpen();
        System.out.print(description + ": ");

        while (!io.hasNextDouble()) {
            System.out.println("Invalid Response. Please enter a decimal number.");
            System.out.print(description + ": ");
            io.nextLine();
        }

        double num = io.nextDouble();
        io.nextLine();
        return num;
    }

    /**
     * Reads a double within a specified range without confirmation.
     *
     * @param description prompt shown to the user
     * @param min inclusive lower bound
     * @param max exclusive upper bound
     * @return validated double within range
     */
    public double nextDoubleNoCon(String description, double min, double max) {
        ensureOpen();
        if (max <= min) throw new IllegalArgumentException("max must be greater than min");

        double num;
        do {
            System.out.print(description + ": ");

            while (!io.hasNextDouble()) {
                System.out.println("Invalid Response. Please enter a decimal number.");
                System.out.print(description + ": ");
                io.nextLine();
            }

            num = io.nextDouble();
            io.nextLine();

            if (num < min || num >= max) {
                System.out.println("Please enter a value such that " + min + " <= num < " + max);
            }

        } while (num < min || num >= max);

        return num;
    }

    // ============================================================
    // DOUBLE INPUT — WITH CONFIRMATION
    // ============================================================

    /**
     * Reads a double with user confirmation.
     *
     * @param description prompt shown to the user
     * @return confirmed double
     */
    public double nextDoubleCon(String description) {
        ensureOpen();
        while (true) {
            double num = nextDoubleNoCon(description);
            if (nextBoolean("You entered \"" + num + "\". Is this correct")) {
                return num;
            }
            System.out.println("Try again.");
        }
    }

    /**
     * Reads a double within a range with user confirmation.
     *
     * @param description prompt shown to the user
     * @param min inclusive lower bound
     * @param max exclusive upper bound
     * @return confirmed double within range
     */
    public double nextDoubleCon(String description, double min, double max) {
        ensureOpen();
        while (true) {
            double num = nextDoubleNoCon(description, min, max);
            if (nextBoolean("You entered \"" + num + "\". Is this correct")) {
                return num;
            }
            System.out.println("Try again.");
        }
    }

    // ============================================================
    // BOOLEAN INPUT
    // ============================================================

    /**
     * Reads a boolean-like response from the user.
     * Accepts: y, yes, 1, true, t, n, no, 2, false, f
     *
     * @param description prompt shown to the user
     * @return true or false based on user input
     */
    public boolean nextBoolean(String description) {
        ensureOpen();
        while (true) {
            System.out.print(description + " (y/n): ");
            String str = io.nextLine().trim().toLowerCase();

            switch (str) {
                case "y":
                case "yes":
                case "1":
                case "true":
                case "t":
                    return true;

                case "n":
                case "no":
                case "2":
                case "false":
                case "f":
                    return false;

                default:
                    System.out.println("Valid responses: y, yes, 1, true, t, n, no, 2, false, f");
            }
        }
    }

    // ============================================================
    // STRING INPUT
    // ============================================================

    /**
     * Reads a line of text without confirmation.
     *
     * @param description prompt shown to the user
     * @return the entered line
     */
    public String nextLineNoCon(String description) {
        ensureOpen();
        System.out.print(description + ": ");
        return io.nextLine();
    }

    /**
     * Reads a line of text with user confirmation.
     *
     * @param description prompt shown to the user
     * @return confirmed line of text
     */
    public String nextLineCon(String description) {
        ensureOpen();
        while (true) {
            System.out.print(description + ": ");
            String line = io.nextLine();

            if (nextBoolean("You entered:\n\t\"" + line + "\"\nIs this correct")) {
                return line;
            }
            System.out.println("Try again.");
        }
    }
}
