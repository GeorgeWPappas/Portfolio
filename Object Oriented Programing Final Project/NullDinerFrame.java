import javax.swing.event.*;
import java.awt.*; 
import javax.swing.*; 

import java.text.NumberFormat;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

import java.util.ArrayList;

class ItemTile extends JPanel {
    private MenuItem item = null;

    private Boolean isAppetizer = false;
    public void setAppetizer() { isAppetizer = true; }

    private Boolean isSandwich = false;
    public void setSandwich() { isSandwich = true; }

    private Boolean isBurger = false;
    public void setBurger() { isBurger = true; }

    private Boolean isDrink = false;
    public void setDrink() { isDrink = true; }

    private Boolean isDessert = false;
    public void setDessert() { isDessert = true; }

    private Boolean isOrderItem = false;
    public void setOrderItem() {isOrderItem = true; }

    ItemTile() {
        super();
    }

    ItemTile(MenuItem itemIn) {
        super();
        item = itemIn;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (isAppetizer) {
            g.setColor(new Color(255,140,0)); // Sets Appetizer to Orange.
        } else if (isSandwich) {
            g.setColor(new Color(175,31,36)); // Sets Sandwich to Red.
        } else if (isBurger) {
            g.setColor(new Color(11,91,170)); // Stes Burger to Blue.
        } else if (isDrink) {
            g.setColor(new Color(255,255,0)); // Sets Drink to Yellow.
        } else if (isOrderItem) {
            g.setColor(new Color(187,244,234)); // sets order tiles to light blue
        } else if (isDessert) {
            g.setColor(new Color(0,204,0)); // Sets Dessert to Green.
        }

        g.fillRect (10, 10, panelWidth-20, panelHeight-20);

        g.setColor(new Color(0,0,0)); // Sets Text to Black.

        final int fontSize=18;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-115;
        int stringY = (panelHeight/2)+30;
        
        if (item != null) {
            String printedName = item.toString();
            g.drawString(printedName,stringX,stringY);
        }
    }
}

public class NullDinerFrame extends JFrame {
    // Arraylists to hold tiles on the menu.
    private ArrayList<ItemTile> appTileList;
    private ArrayList<ItemTile> sandTileList;
    private ArrayList<ItemTile> burgTileList;
    private ArrayList<ItemTile> drinkTileList;
    private ArrayList<ItemTile> dessTileList;

    private ArrayList<ItemTile> orderedTileList;

    private Order order;
    private NullDinerMenu menu;

    private NumberFormat nf = NumberFormat.getCurrencyInstance();

    NullDinerFrame(Order orderIn, NullDinerMenu menuIn) {
        order = orderIn;    
        menu = menuIn;

        setBounds(100,100,1500,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Holds menu. 
        JPanel itemGridPanel = new JPanel();
        itemGridPanel.setLayout(new GridLayout(5,3));
        
        // Holds ordered items.
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        // Splits jframe into menu and order.
        JSplitPane splitPane = new JSplitPane(SwingConstants.VERTICAL, itemGridPanel, orderPanel);
        splitPane.setDividerLocation(1000);
        add(splitPane);

        // Checkouts and exits frame.
        JButton checkoutButton = new JButton("Checkout");
        buttonPanel.add(checkoutButton);
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                order.checkout();
                System.out.println("CHECKING OUT... Press enter to continue.");
                buttonPanel.remove(checkoutButton);
                setVisible(false);
                dispose();
            }
        });

        JPanel helpPanel = new JPanel();
        JLabel help1 = new JLabel("Click on an item in the menu on the left to add to your order.");
        JLabel help2 = new JLabel("To remove an item from your order on the right, click on the item.");
        helpPanel.add(help1);
        helpPanel.add(help2);
        contentPane.add(helpPanel, BorderLayout.NORTH);

        JLabel priceField = new JLabel("Total: " + nf.format(order.getTotal()) + ", Tax: " + nf.format(order.getTax())
            + ", Subtotal: " + nf.format(order.getSubtotal()));
        buttonPanel.add(priceField);

        SoundClip sound = new SoundClip(); // Creates a new instance of the SoundClip class.

        // Fills out the frame with menu items.
        appTileList = new ArrayList<ItemTile>();
        for (int i=0; i<menu.getAppArray().size(); i++) {
            MenuItem ap = menu.getAppetizer(i);
            ItemTile appTile = new ItemTile(ap);
            appTile.setAppetizer();
            appTileList.add(appTile);
            itemGridPanel.add(appTile);

            appTile.addMouseListener(new MouseListener() {
                public void mousePressed(MouseEvent me) { // Adds to order.
                    sound.playSound(); // Plays sound.
                    addToOrder(order, orderPanel, ap, priceField);
                    updatePriceField(priceField);
                }

                public void mouseReleased(MouseEvent me) {}
                public void mouseClicked(MouseEvent me) {}
                public void mouseExited(MouseEvent me) {}
                public void mouseEntered(MouseEvent me) {} 
            });
        }

        sandTileList = new ArrayList<ItemTile>();
        for (int i=0; i<menu.getSandArray().size(); i++) {
            MenuItem s = menu.getSandwich(i);
            ItemTile sandTile = new ItemTile(s);
            sandTile.setSandwich();
            sandTileList.add(sandTile);
            itemGridPanel.add(sandTile);

            sandTile.addMouseListener(new MouseListener() {
                public void mousePressed(MouseEvent me) {
                    sound.playSound(); // Plays sound.
                    addToOrder(order, orderPanel, s, priceField);
                    updatePriceField(priceField);
                }

                public void mouseReleased(MouseEvent me) {}
                public void mouseClicked(MouseEvent me) {}
                public void mouseExited(MouseEvent me) {}
                public void mouseEntered(MouseEvent me) {} 
            });
        }

        burgTileList = new ArrayList<ItemTile>();
        for (int i=0; i<menu.getBurgArray().size(); i++) {
            MenuItem b = menu.getBurger(i);
            ItemTile burgTile = new ItemTile(b);
            burgTile.setBurger();
            burgTileList.add(burgTile);    
            itemGridPanel.add(burgTile);

            burgTile.addMouseListener(new MouseListener() {
                public void mousePressed(MouseEvent me) {
                    sound.playSound(); // Plays sound.
                    addToOrder(order, orderPanel, b, priceField);
                    updatePriceField(priceField);
                }

                public void mouseReleased(MouseEvent me) {}
                public void mouseClicked(MouseEvent me) {}
                public void mouseExited(MouseEvent me) {}
                public void mouseEntered(MouseEvent me) {} 
            });
        }

        drinkTileList = new ArrayList<ItemTile>();
        for (int i=0; i<menu.getDrinkArray().size(); i++) {
            MenuItem dr = menu.getDrink(i);
            ItemTile drinkTile = new ItemTile(dr);
            drinkTile.setDrink();
            drinkTileList.add(drinkTile);
            itemGridPanel.add(drinkTile);

            drinkTile.addMouseListener(new MouseListener() {
                public void mousePressed(MouseEvent me) {
                    sound.playSound(); // Plays sound.
                    addToOrder(order, orderPanel, dr, priceField);
                    updatePriceField(priceField);
                }

                public void mouseReleased(MouseEvent me) {}
                public void mouseClicked(MouseEvent me) {}
                public void mouseExited(MouseEvent me) {}
                public void mouseEntered(MouseEvent me) {} 
            });
        }

        dessTileList = new ArrayList<ItemTile>();
        for (int i=0; i<menu.getDessArray().size(); i++) {
            MenuItem ds = menu.getDessert(i);
            ItemTile dessTile = new ItemTile(ds);
            dessTile.setDessert();
            dessTileList.add(dessTile);
            itemGridPanel.add(dessTile);

            dessTile.addMouseListener(new MouseListener() {
                public void mousePressed(MouseEvent me) {
                    sound.playSound(); // Plays sound.
                    addToOrder(order, orderPanel, ds, priceField);
                    updatePriceField(priceField);
                }

                public void mouseReleased(MouseEvent me) {}
                public void mouseClicked(MouseEvent me) {}
                public void mouseExited(MouseEvent me) {}
                public void mouseEntered(MouseEvent me) {} 
            });
        }
    }

    // Updates the total, tax, and subtotal info on the JLabel.
    public void updatePriceField(JLabel label) {
        label.setText("Total: " + nf.format(order.getTotal()) + ", Tax: " + nf.format(order.getTax())
            + ", Subtotal: " + nf.format(order.getSubtotal()));
        revalidate();
        repaint();
    }

    // Adds an item to the order.
    public void addToOrder(Order orderIn, JPanel orderPanelIn, MenuItem mi, JLabel priceFieldIn) {
        orderIn.addItem(mi);
        System.out.println("Adding: " + mi);
        orderIn.calcTotal();
        orderIn.calcTax();
        orderIn.calcSubtotal();
        System.out.println(order);

        ItemTile orderedTile = new ItemTile(mi);
        orderedTile.setOrderItem();
        orderedTile.addMouseListener(new MouseListener() { // Creates mouselistener that removes item from order when clicked.
            public void mousePressed(MouseEvent me) { 
                removeFromOrder(orderIn, orderPanelIn, mi, orderedTile);
                updatePriceField(priceFieldIn);
            }

            public void mouseReleased(MouseEvent me) {}
            public void mouseClicked(MouseEvent me) {}
            public void mouseExited(MouseEvent me) {}
            public void mouseEntered(MouseEvent me) {} 
            });

        orderPanelIn.add(orderedTile);
        orderPanelIn.revalidate();
        orderPanelIn.repaint();
    } 

    // Removes item from order.
    public void removeFromOrder(Order orderIn, JPanel orderPanelIn, MenuItem mi, ItemTile it) {
        orderIn.removeItem(mi);
        orderPanelIn.remove(it);
        System.out.println("Removing: " + mi);
        orderIn.calcTotal();
        orderIn.calcTax();
        orderIn.calcSubtotal();
        System.out.println(order);
    }
}