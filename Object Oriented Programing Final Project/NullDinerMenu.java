import java.util.ArrayList; 

public class NullDinerMenu {
    private ArrayList<MenuItem> appetizers = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> sandwiches = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> burgers = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> drinks = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> desserts = new ArrayList<MenuItem>();

    public NullDinerMenu() {
        fillMenu();
    }

    public void fillMenu() {

        // Appetizers
        appetizers.add(new MenuItem("Appetizer", "A0", "Chicken Tenders", 2.99)); // #0
        appetizers.add(new MenuItem("Appetizer", "A1", "Onion Rings", 2.99)); //#1
        appetizers.add(new MenuItem("Appetizer", "A2", "Mozzarella Sticks", 2.99)); //#2

        // Sandwiches
        sandwiches.add(new MenuItem("Sandwich", "S0", "Null Reuben", 6.99)); // #0
        sandwiches.add(new MenuItem("Sandwich", "S1", "Tuna Sandwich", 6.99)); // #1
        sandwiches.add(new MenuItem("Sandwich", "S2", "Ham Sandwich", 5.99)); // #2

        // Burgers
        burgers.add(new MenuItem("Burger", "B0", "Null Burger", 7.99)); // #0
        burgers.add(new MenuItem("Burger", "B1", "Double Cheeseburger", 8.99)); //#1
        burgers.add(new MenuItem("Burger", "B2", "Float Cheeseburger", 8.99)); //#2
 
        // Drinks
        drinks.add(new MenuItem("Drink", "Dr0", "Nullka Cola", 1.25)); // #0
        drinks.add(new MenuItem("Drink", "Dr1", "Null Shake", 1.99)); // #1
        drinks.add(new MenuItem("Drink", "Dr2", "Coffee", 1.99)); // #2

        // Desserts
        desserts.add(new MenuItem("Dessert", "Ds0", "Cherry Pie", 2.99)); // #0
        desserts.add(new MenuItem("Dessert", "Ds1", "Strawberry Shortcake", 2.99)); // #1
        desserts.add(new MenuItem("Dessert", "Ds2", "Fudge Sundae", 3.99)); // #2
    }

    public MenuItem getAppetizer(int itemNum) { // Gets an appetizer
        return appetizers.get(itemNum);
    }

    public MenuItem getSandwich(int itemNum) {
        return sandwiches.get(itemNum);
    }

    public MenuItem getBurger(int itemNum) {
        return burgers.get(itemNum);
    }

    public MenuItem getDrink(int itemNum) {
        return drinks.get(itemNum);
    }

    public MenuItem getDessert(int itemNum) {
        return desserts.get(itemNum);
    }

    public ArrayList<MenuItem> getAppArray() {
        return appetizers;
    }

    public ArrayList<MenuItem> getSandArray() {
        return sandwiches;
    }

    public ArrayList<MenuItem> getBurgArray() {
        return burgers;
    }

    public ArrayList<MenuItem> getDrinkArray() {
        return drinks;
    }

    public ArrayList<MenuItem> getDessArray() {
        return desserts;
    }
}