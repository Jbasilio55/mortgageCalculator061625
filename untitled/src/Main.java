import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //title
        System.out.println("-----Mortgage Calculator----\n");

        byte percent = 100; // 100%
        byte monthsInAYear = 12; // 12 month -> 1 year

        //open scanner
        Scanner scanner = new Scanner(System.in);

        // get total amount of principal
        System.out.print("Principal: ");
        int principal  = scanner.nextInt();

        //get annual rate
        System.out.print("Annual Interest Rate: ");
        double annualRate = scanner.nextDouble() / percent;
        double monthlyRate = annualRate / monthsInAYear;

        //get year and then convert to number of payment by multiplying -> (years * months)
        System.out.print("Period (Years): ");
        int period = scanner.nextInt();
        int numberOfPayments  = period * monthsInAYear;

        //mortgage formula
        double mortgage = principal *
                (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments) /
                        (Math.pow(1 + monthlyRate, numberOfPayments) - 1));

        // format amount from double to dollar amount
        String formatMortgage = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("Mortgage: " + formatMortgage);

        // close scanner
        scanner.close();
    }
}