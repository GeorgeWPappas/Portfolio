import java.text.NumberFormat;

public class MenuItem {
    private String itemType;
    private String itemName;
    private double price;
    private String menuIndex;
    private NumberFormat nf = NumberFormat.getCurrencyInstance();

    public MenuItem(String itemTypeIn, String menuIndexIn, String itemNameIn, double priceIn) {
        this.itemType = itemTypeIn;
        this.itemName = itemNameIn;
        this.price = priceIn;
        this.menuIndex = menuIndexIn;
    }

    public String getName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return menuIndex + " - " + itemName + ", " + nf.format(price) + "\n";
    }
}