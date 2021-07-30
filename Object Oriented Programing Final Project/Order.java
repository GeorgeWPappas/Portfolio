import java.util.ArrayList;
import java.text.NumberFormat;

// For printing Receipt.
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Order {
    private String customerName;
    private ArrayList<MenuItem> orderList = new ArrayList<MenuItem>();
    private NumberFormat nf = NumberFormat.getCurrencyInstance();
    private double total;
    private double tax;
    private double subtotal;
    private Boolean checkedOut;
    private String saveFileName = "CustomerReceipt.txt";

    public Order(String nameIn) {
        this.customerName = nameIn;
        this.checkedOut = false;
    }

    public MenuItem getItem(int itemNumber) {
        return orderList.get(itemNumber);
    }

    public void addItem(MenuItem item) {
        orderList.add(item);
    }

    public void removeItem(MenuItem item) {
        orderList.remove(item);
    }

    public void calcTotal() {
        total = 0; // Resets the total
        for (MenuItem item : orderList) {
            total += item.getPrice();
        }
    }

    public void calcTax() {
        calcTotal();
        tax = total * 0.0625;
    }

    public void calcSubtotal() {
        subtotal = tax + total;
    }

    public double getTotal() {
        return total;
    }

    public double getTax() {
        return tax;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Boolean getCheckoutStatus() {
        return checkedOut;
    }

    public void checkout() {
        checkedOut = true;
    }

    public ArrayList<MenuItem> getOrderList() {
        return orderList;
    }

    public String toString() { // Receipt info.

        String printOrder = "\n**************************\nName: " + customerName + "\n**************************\n";
        for (MenuItem item : orderList) {
            printOrder += item;
        }

        printOrder += "**************************\nTotal: " + nf.format(total);
        printOrder += "\nTax: " + nf.format(tax);
        printOrder += "\nSubtotal: " + nf.format(subtotal) + "\n**************************\n";

        try {
            if (checkedOut) {
                printReceiptToFile(printOrder);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Could not save receipt");
        }

        return printOrder;
    }

    public void printReceiptToFile(String receipt) throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(saveFileName); // Create the write to file.
        printWriter.println(receipt); // Writes receipt to file.
        printWriter.close(); // Closes printWriter.

    }
}