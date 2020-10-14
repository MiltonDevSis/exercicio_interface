package application;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.OnlinePaymentService;
import model.service.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner entrada = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract Data");
        System.out.println("Number: ");
        int number = entrada.nextInt();
        System.out.println("Date: ");
        Date date = sdf.parse(entrada.next());
        System.out.println("Contract value:");
        double contractValue = entrada.nextDouble();

        Contract contract = new Contract(number, date, contractValue);

        System.out.println("Enter number of Installments:");
        int installments = entrada.nextInt();

        ContractService cs = new ContractService(new PaypalService());

        cs.processContract(contract, installments);

        System.out.println("Installments:");
        for (Installment it: contract.getInstallments()) {
            System.out.println(it);
        }
        entrada.close();
    }
}
