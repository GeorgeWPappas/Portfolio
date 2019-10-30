/** 
 * @author George Pappas
*/

// Assignment: Mosaic

// Variable declarations.
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;


public class Mosaic {
    public static void main(String[] args) {
        System.out.println("Starting  Mosaic...");
        System.out.println("Start paint***");

        TileFrame myTileFrame = new TileFrame();
        myTileFrame.setVisible(true);
    }
}

class Tile extends JPanel {
    // Variable declarations.
    private int red, green, blue;
    private String letter;
    private int shape;
    private String[] letterArray = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q",
        "R","S","T","U","V","W","X","Y","Z"};

    Tile() {
        super();
        SetRandomValues(0,1);
    }

    final public void SetRandomValues(int min, int max) { // Generates random values.
        // Generates the values for Red, Green, and Blue.
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);

        letter = letterArray[GetNumberBetween(0,25)]; // Generates a letter for each tile.
        shape = GetNumberBetween(min,max); // Generates value for shape.
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min+1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        g.setColor(new Color(red,green,blue));

        if (shape == 0) {
            g.fillRect(0, 0, panelWidth, panelHeight); // Generates a rectangle
        } else {
            g.fillOval(0, 0, panelWidth, panelHeight); // Generates a circle
        }

        g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green),GetContrastingColor(blue)));
        System.out.println(toString()); // Prints out tile's components to console.

        final int fontSize = 25;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-9;
        int stringY = (panelHeight/2)+10;
        g.drawString(letter,stringX,stringY);
    }

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }

    public String toString() {
        if (shape == 0) {
            // Prints out shape to console. In this case, the case will be a square.
            return "Square - " + letter + " - " + "Red: " + red + ", Green: " + green + ", Blue: " + blue;
        } else {
            // Prints out shape to console. In this case, the case will be a circle.
            return "Circle - " + letter + " - " + "Red: " + red + ", Green: " + green + ", Blue: " + blue;
        }
    }
}

class TileFrame extends JFrame implements ActionListener {
    private ArrayList<Tile> tileList;

    public TileFrame() {
        setBounds(200,200,800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Creates randomize button
        JButton randomize = new JButton("Randomize");
        buttonPanel.add(randomize);
        randomize.addActionListener(this);

        // Create all square button
        JButton allSquare = new JButton("All Square");
        buttonPanel.add(allSquare);
        allSquare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Tile tile : tileList) {
                    tile.SetRandomValues(0,0);
                }
                repaint();
            }
        });
       
        // Create all circle button
        JButton allCircle = new JButton("All Circle");
        buttonPanel.add(allCircle);
        allCircle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Tile tile : tileList) {
                    tile.SetRandomValues(1,1);
                }
                repaint();
            }
        });

        JPanel GridPanel = new JPanel();
        contentPane.add(GridPanel, BorderLayout.CENTER);
        GridPanel.setLayout(new GridLayout(12,12));

        tileList = new ArrayList<Tile>();
        for(int i=1; i<145; i++) {
            Tile tile = new Tile();
            tileList.add(tile);
            GridPanel.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Start paint***");
        for(Tile tile : tileList) {
            tile.SetRandomValues(0,1);
        }
        repaint();
    }
}
