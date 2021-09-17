import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Purchases Operations.
 */
public class PurchasesOperations {

    private static final int ACTIVE_MONTH = 8;

    /**
     * Sum up all purchases of each customer by month.
     */
    public Map<Integer, ArrayList<Purchase>> sumUpEachCustomerPurchasesByMonth(Map<Integer, ArrayList<Purchase>> allPurchases) {
        for (Map.Entry<Integer, ArrayList<Purchase>> entry : allPurchases.entrySet()) {
            ArrayList<Purchase> customerPurchases = entry.getValue();
            sumUpOneCustomerPurchasesByMonth(customerPurchases);
        }
        return allPurchases;
    }

    /**
     * Sum up all purchases of one customer by month.
     */
    public void sumUpOneCustomerPurchasesByMonth(ArrayList<Purchase> customerPurchases) {
        customerPurchases.sort(Comparator.comparing(Purchase::getDate));
        for (int i = 0; i < customerPurchases.size() - 1; i++) {
            Purchase purchase = customerPurchases.get(i);
            Purchase nextPurchase = customerPurchases.get(i + 1);
            if (getMonthNumber(purchase.getDate()) == getMonthNumber(nextPurchase.getDate()) &&
                    purchase.getPaymentType().equals(nextPurchase.getPaymentType())) {
                purchase.setCost(purchase.getCost() + nextPurchase.getCost());
                customerPurchases.remove(i + 1);
            }
            if (getMonthNumber(purchase.getDate()) == ACTIVE_MONTH) {
                purchase.getCustomer().setActive(true);
            }
        }
    }

    /**
     * Sort purchases of all customers by month.
     */
    public Map<Integer, ArrayList<Purchase>> sortAllCustomersPurchasesByMonth(Map<Integer, ArrayList<Purchase>> allPurchases) {
        Map<Integer, ArrayList<Purchase>> sortedAllPurchasesByMonth = new HashMap<>();
        ArrayList<Purchase> sortedOneMonthPurchases;
        for (Map.Entry<Integer, ArrayList<Purchase>> entry : allPurchases.entrySet()) {
            ArrayList<Purchase> customerPurchases = entry.getValue();
            for (int i = 0; i < customerPurchases.size(); i++) {
                if (sortedAllPurchasesByMonth.get(getMonthNumber(customerPurchases.get(i).getDate())) ==
                        null) {
                    sortedOneMonthPurchases = new ArrayList<>();
                } else {
                    sortedOneMonthPurchases = sortedAllPurchasesByMonth.get(getMonthNumber(customerPurchases.get(i).getDate()));
                }
                sortedOneMonthPurchases.add(customerPurchases.get(i));
                sortedAllPurchasesByMonth.put(getMonthNumber(customerPurchases.get(i).getDate()), sortedOneMonthPurchases);
            }
        }
        return sortedAllPurchasesByMonth;
    }

    /**
     * Print a report of all purchases of all customers for all months taking into account the activity of customers.
     */
    public void printReportAllMonthsWithActivity(Map<Integer, ArrayList<Purchase>> allPurchases) {
        System.out.println("Active buyers");
        for (Map.Entry<Integer, ArrayList<Purchase>> entry : allPurchases.entrySet()) {
            System.out.println(getMonthString(entry.getKey()));
            printReportOneMonthWithActivity(entry.getValue(), true);
        }
        System.out.println("\nInactive buyers");
        for (Map.Entry<Integer, ArrayList<Purchase>> entry : allPurchases.entrySet()) {
            System.out.println(getMonthString(entry.getKey()));
            printReportOneMonthWithActivity(entry.getValue(), false);
        }
    }

    /**
     * Print a report of all purchases of all customers for one month taking into account the activity of customers.
     */
    public void printReportOneMonthWithActivity(ArrayList<Purchase> oneMonthPurchases, boolean active) {
        for (int i = 0; i < oneMonthPurchases.size(); i++) {
            if (active && oneMonthPurchases.get(i).getCustomer().getActive()
                    || !active && !oneMonthPurchases.get(i).getCustomer().getActive()) {
                printReportOneMonth(oneMonthPurchases, i);
            }
        }
    }

    /**
     * Print a report of all purchases of all customers for one month.
     */
    public void printReportOneMonth(ArrayList<Purchase> oneMonthPurchases, int i) {
        Purchase purchase = oneMonthPurchases.get(i);
        Purchase nextPurchase;
        System.out.print("       " + purchase.getCustomer().getName() + " " + purchase.getCustomer().getSurname() +
                " (" + purchase.getCustomer().getPhoneNumber() + ") : $");
        if (i != oneMonthPurchases.size() - 1) {
            nextPurchase = oneMonthPurchases.get(i + 1);
            if (purchase.getCustomer() != nextPurchase.getCustomer()) {
                System.out.println(purchase.getCost() + " (" + purchase.getPaymentType() + ": $" + purchase.getCost() + ")");
            } else {
                System.out.println(purchase.getCost() + nextPurchase.getCost() + " (" + purchase.getPaymentType() +
                        ": $" + purchase.getCost() + ", " + nextPurchase.getPaymentType() + ": $" +
                        nextPurchase.getCost() + ")");
                oneMonthPurchases.remove(i + 1);
            }
        } else {
            System.out.println(purchase.getCost() + " (" + purchase.getPaymentType() + ": $" + purchase.getCost() + ")");
        }
    }

    /**
     * Get month number.
     */
    public int getMonthNumber(int date) {
        return date / 100 % 100;
    }

    /**
     * Get month name.
     */
    public String getMonthString(int monthNumber) {
        return switch (monthNumber) {
            case 1:
                yield "January";
            case 2:
                yield "February";
            case 3:
                yield "March";
            case 4:
                yield "April";
            case 5:
                yield "May";
            case 6:
                yield "June";
            case 7:
                yield "July";
            case 8:
                yield "August";
            case 9:
                yield "September";
            case 10:
                yield "October";
            case 11:
                yield "November";
            case 12:
                yield "December";
            default:
                yield "Wrong";
        };
    }
}
