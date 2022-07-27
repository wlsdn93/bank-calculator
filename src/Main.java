public class Main {
    public static void main(String[] args) {
//        // 예금 단리 비과세
//        System.out.println(BankingCalculator.depositCalculator(1000000, 12, 3.33, InterestType.SIMPLE, TaxType.FREE));
//        // 예금 복리 일반과세
//        System.out.println(BankingCalculator.depositCalculator(1000000, 12, 3.33, InterestType.COMPOUND, TaxType.DEFAULT));
//        // 적금 우대과세
//        System.out.println(BankingCalculator.installmentSavingCalculator(300000, 3, 2.71, TaxType.PREFERENTIAL));
        System.out.println("만기상환");
        System.out.println(BankingCalculator.loanCalculator(5000000, 12, 12, RepaymentType.REPAYMENT_AT_MATURITY_DATE));
//        System.out.println("원금균등상환");
//        System.out.println(BankingCalculator.loanCalculator(5000000, 12, 3, RepaymentType.EQUAL_REPAYMENT_OF_PRINCIPAL));
        System.out.println("원리금균등상환");
        System.out.println(BankingCalculator.loanCalculator(5000000, 12, 4, RepaymentType.EQUAL_REPAYMENT_OF_PRINCIPAL_AND_INTEREST));
    }
}