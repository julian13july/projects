/**
 * Class Purchase.
 */
public class Purchase {
    private int date;
    private Customer customer;
    private int cost;
    private String paymentType;

    public Purchase(int date, Customer customer, int cost, String paymentType) {
        this.date = date;
        this.customer = customer;
        this.cost = cost;
        this.paymentType = paymentType;
    }

    public int getDate() {
        return this.date;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPaymentType() {
        return this.paymentType;
    }
}