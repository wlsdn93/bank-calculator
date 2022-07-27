import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BankingCalculator {
    public static double depositCalculator(double deposit, int period, double interestRate, InterestType interestType, TaxType taxType) {
        double result = 0;
        switch (interestType) {
            case SIMPLE:
                result = (deposit * Math.pow(1 + interestRate/100, period/12) - deposit) * (1 - taxType.taxRate) + deposit;
                break;
            case COMPOUND:
                result = (deposit * Math.pow(1 + interestRate/100/12, period) - deposit) * (1 - taxType.taxRate) + deposit;
                break;
        }
        return Math.round(result);
    }
    public static double installmentSavingCalculator(double monthlyDeposit, double period, double interestRate, TaxType taxType) {
        double interest = Math.floor((monthlyDeposit * period * (period + 1) / 2 * (interestRate/100) / 12) / 10) * 10;
        return Math.floor(interest * (1 - taxType.taxRate) + monthlyDeposit * period);
    }
    public static List<RepaymentDetail> loanCalculator(double loan, int period, double interestRate, RepaymentType repaymentType) {
        LocalDate repaymentDueDate = LocalDate.now(); // 대출일
        LocalDate nextRepaymentDueDate = repaymentDueDate.plusMonths(1);
        List<RepaymentDetail> resultList = new ArrayList<>();
        double total = 0;
        double remainLoan = loan;
        switch (repaymentType) {
            case REPAYMENT_AT_MATURITY_DATE:
                for (int month = 1; month <= period; month++) {
                    long days = ChronoUnit.DAYS.between(repaymentDueDate, nextRepaymentDueDate);
                    double principal = 0;
                    double interest = Math.round(loan * interestRate / 100 * days / 365);
                    double repayment = principal + interest;
                    resultList.add(new RepaymentDetail(nextRepaymentDueDate, days, principal, interest, remainLoan, repayment));
                    repaymentDueDate = nextRepaymentDueDate;
                    nextRepaymentDueDate = repaymentDueDate.plusMonths(1);
                    total += interest;
                }
                resultList.get(period - 1).principal = loan;
                resultList.get(period - 1).repayment += loan;
                resultList.get(period - 1).remainLoan = 0;
                System.out.println(total);
                break;
            case EQUAL_REPAYMENT_OF_PRINCIPAL:
                for (int month = 1; month <= period; month++) {
                    long days = ChronoUnit.DAYS.between(repaymentDueDate, nextRepaymentDueDate);
                    double principal = remainLoan < Math.round(loan / period) ? remainLoan : Math.ceil(loan / period);
                    double interest = Math.round(loan * interestRate / 100 * days / 365);
                    double repayment = principal + interest;
                    remainLoan = Math.ceil(remainLoan - principal);
                    resultList.add(new RepaymentDetail(nextRepaymentDueDate, days, principal, interest, remainLoan, repayment));
                    repaymentDueDate = nextRepaymentDueDate;
                    nextRepaymentDueDate = repaymentDueDate.plusMonths(1);
                }
                break;
            case EQUAL_REPAYMENT_OF_PRINCIPAL_AND_INTEREST:
                for (int month = 1; month <= period; month++) {
                    long days = ChronoUnit.DAYS.between(repaymentDueDate, nextRepaymentDueDate);
                    double repayment = Math.round(loan * interestRate/1200 * Math.pow((1+interestRate/1200), period) / (Math.pow((1+interestRate/1200), period) - 1));
                    double interest = Math.round(remainLoan * interestRate / 100 * days / 365);
                    double principal = repayment > remainLoan ? remainLoan : repayment - interest;
                    remainLoan = Math.floor(remainLoan - principal);
                    resultList.add(new RepaymentDetail(nextRepaymentDueDate, days, principal, interest, remainLoan, repayment));
                    repaymentDueDate = nextRepaymentDueDate;
                    nextRepaymentDueDate = repaymentDueDate.plusMonths(1);
                }
                break;
        }
        return resultList;
    }
}
