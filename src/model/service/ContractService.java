package model.service;

import model.entities.Contract;
import model.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){

        double basicQuota = contract.getTotalValue() / months;
            // valor total dividido pelo quantidade de meses.
        for (int i = 1; i <= months; i++){
            double updateQuote = basicQuota + onlinePaymentService.interest(basicQuota, i);
            // basicQuota + taxa de juros(e o valor de um mes)
            double fullQuota = updateQuote + onlinePaymentService.paymentFee(updateQuote);
            // fullquota é o valor + taxa, em cima do updatequota
            Date dueDate = addMonth(contract.getDate(), i);
            contract.getInstallments().add(new Installment(dueDate, fullQuota));
        }
    }

    private Date addMonth(Date date, int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }
}
