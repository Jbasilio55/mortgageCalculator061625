import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Mortgage Calculator----\n");

        int principal;
        double annualRate;
        int period;

        //Get principal
        principal = (int)readNumber(
                "Principal ($1k - $1M): ",
                1000,
                1_000_000,
                "Enter number between 1,000 and 1,000,000"
        );

        // Get annual interest rate
        annualRate = readNumber(
                "Annual Interest Rate: ",
                1,
                30,
                "Enter a value greater than 0 and less than or equal to 30"
        );

        // Get loan period
        period = (int)readNumber(
                "Period (Years): ",
                1,
                30,
                "Enter a value between 1 and 30"
        );

        // Call mortgage calculator
        double mortgage = getMortgage(principal, annualRate, period);
        String formatMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + formatMortgage);

        scanner.close();
    }

    public static double readNumber(String prompt, int min, int max, String errorPrompt){
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println(errorPrompt);
        }

        return value;
    }

    public static double getMortgage(int principal, double annualRate, int period) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        double monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = period * MONTHS_IN_YEAR;

        return principal *
                (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) /
                (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }
}