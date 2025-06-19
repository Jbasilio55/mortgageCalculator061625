import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("-----Mortgage Calculator----\n");

        //Get principal
        int principal = (int)readNumber(
                "Principal ($1k - $1M): ", 1000, 1_000_000);

        // Get annual interest rate
        double annualRate = readNumber(
                "Annual Interest Rate: ", 1, 30);

        // Get loan period
        int period = (int)readNumber("Period (Years): ", 1, 30);

        // Call mortgage calculator
        double mortgage = getMortgage(principal, annualRate, period);
        String formatMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + formatMortgage);

        getLeftOverLoanAmount(principal, annualRate, period);
    }

    public static double readNumber(String prompt, int min, int max){
        Scanner scanner = new Scanner(System.in);

        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between "+ min +" and "+max);
        }

        return value;
    }

    public static double getMortgage(int principal, double annualRate, int period) {
        final byte MONTHS_IN_YEAR = 12; // months in a year
        final byte PERCENT = 100; // 100%

        double monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR; //monthly rate
        int numberOfPayments = period * MONTHS_IN_YEAR; // number of total payments

        return principal *
                (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) /
                (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }

    private static void getLeftOverLoanAmount(int principal, double annualRate, int period) {
        final byte MONTHS_IN_YEAR = 12; // months in a year
        final byte PERCENT = 100; // 100%

        double monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR; //monthly rate
        int numberOfPayments = period * MONTHS_IN_YEAR; // number of total payments

        System.out.println("\nPAYMENT SCHEDULE");
        System.out.println("----------------");

        // L[(1 + c)**n – (1 + c)**p]/[(1 + c)**n – 1]

        for(int i = 1; i < numberOfPayments + 1; i++){
            double amountOwed = principal *
                    (Math.pow(1 + monthlyRate, numberOfPayments) - Math.pow(1 + monthlyRate, i)) /
                    (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
            System.out.println(NumberFormat.getCurrencyInstance().format(amountOwed));
        }
    }
}