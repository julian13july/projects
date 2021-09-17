import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * OnlineStore - emulation of purchases in an online store (registration of customers and a log of purchases).
 */
public class OnlineStore {

    private static Map<Integer, ArrayList<Purchase>> allPurchases;

    public static void main(String[] args) {
        allPurchases = new HashMap<>();
        Customer bob = new Customer("Bob", "Petrov", 19880403, "050 123 12 31");
        Customer oleg = new Customer("Oleg", "Sidorov", 19740713, "099 456 45 66");
        Customer michal = new Customer("Michal", "Smith", 19871215, "097 789 78 97");
        makePurchase(new Purchase(20210605, bob, 122, "Card"));
        makePurchase(new Purchase(20210711, oleg, 235, "Cash"));
        makePurchase(new Purchase(20210723, bob, 134, "Card"));
        makePurchase(new Purchase(20210802, michal, 152, "Card"));
        makePurchase(new Purchase(20210812, oleg, 224, "Cash"));
        makePurchase(new Purchase(20210815, oleg, 354, "Cash"));
        makePurchase(new Purchase(20210816, oleg, 178, "Card"));

        PurchasesOperations operation = new PurchasesOperations();
        allPurchases = operation.sumUpEachCustomerPurchasesByMonth(allPurchases);
        allPurchases = operation.sortAllCustomersPurchasesByMonth(allPurchases);
        operation.printReportAllMonthsWithActivity(allPurchases);
    }

    /**
     * Make a purchase - add purchase to the HashMap in the format:
     * key - client id
     * value - client's shopping list as an ArrayList
     */
    public static void makePurchase(Purchase purchase) {
        ArrayList<Purchase> customerPurchases;
        if (allPurchases.get(purchase.getCustomer().getId()) == null) {
            customerPurchases = new ArrayList<>();
        } else {
            customerPurchases = allPurchases.get(purchase.getCustomer().getId());
        }
        customerPurchases.add(purchase);
        allPurchases.put(purchase.getCustomer().getId(), customerPurchases);
    }
}