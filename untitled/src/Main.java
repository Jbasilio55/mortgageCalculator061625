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
        int principal; // initialize principal

        // get total amount of principal
        while(true){
            System.out.print("Principal ($1k - $1M): ");
            principal  = scanner.nextInt();

            // if principal is between 1k - 1M (break)
            if(principal >= 1000 && principal <= 1000000){
                break;
            }else{
                System.out.println("Enter number between 1,000 and 1,000,000");
            }
        }

        double annualRate; // initialize annual rate
        double monthlyRate; // initialize monthly rate

        //get annual rate
        while(true){
            System.out.print("Annual Interest Rate: ");
            annualRate = scanner.nextDouble();

            // if annual rate is between 1 - 30 (break)
            if(annualRate >= 1 && annualRate <= 30){
                annualRate /= percent;
                monthlyRate = annualRate / monthsInAYear;
                break;
            }else{
                System.out.println("Enter a value greater that 0 and less than or equal to 30");
            }
        }

        int period; // initialize period
        int numberOfPayments; // initialize number of payments

        //get year and then convert to number of payment by multiplying -> (years * months)
        while(true){
            System.out.print("Period (Years): ");
            period = scanner.nextInt();

            // if period is between 1 - 30 (break)
            if(period >= 1 && period <= 30){
                numberOfPayments  = period * monthsInAYear;
                break;
            }else{
                System.out.println("Enter a value between 1 and 30");
            }
        }

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