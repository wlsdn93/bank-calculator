import java.text.DecimalFormat;
import java.time.LocalDate;

public class RepaymentDetail {
    public LocalDate repaymentDate;
    public long days;
    public double principal;
    public double interest;
    public double remainLoan;
    public double repayment;

    public RepaymentDetail(LocalDate repaymentDate, long days, double principal, double interest, double remainLoan, double repayment) {
        this.repaymentDate = repaymentDate;
        this.days = days;
        this.principal = principal;
        this.interest = interest;
        this.remainLoan = remainLoan;
        this.repayment = repayment;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return  "상환 일자 = " + repaymentDate +
                ", 일수 = " + days +
                ", 원금 = " + decimalFormat.format(principal) +
                ", 이자 = " + decimalFormat.format(interest) +
                ", 납입합계 = " + decimalFormat.format(repayment) +
                ", 대출잔액 = " + decimalFormat.format(remainLoan) +
                '\n';
    }
}