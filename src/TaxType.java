public enum TaxType {
    FREE( 0), PREFERENTIAL( 0.095), DEFAULT(0.154);
    public final double taxRate;
    TaxType(double taxRate) {
        this.taxRate = taxRate;
    }
}