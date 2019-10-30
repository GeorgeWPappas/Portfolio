// Author: George Pappas
// Assignment: Programing Assignment (PA) 2


// Variable declarations
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;

class Oval extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Sets color and size of rectangle
        Color rectColor = new Color(0,0,60);
        g.setColor(rectColor);
        g.fillRect(0,0,panelWidth,panelHeight);

        // Sets color and size of oval
        Color ovalColor = new Color(8,104,21);
        g.setColor(ovalColor);
        g.fillOval(0,0,300,400);
    }
}

class OvalFrame extends JFrame {
    OvalFrame() {
        // Sets header and size of JFrame
        setTitle("Green");
        setBounds(100,200,300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Oval myOval = new Oval();
        Container contentPane = getContentPane();
        contentPane.add(myOval);
    }
}

public class OvalDrawPlus {
    public static void main(String[] args) {
        System.out.println("OvalDrawPlus Staring...");

        OvalFrame myFrame = new OvalFrame();
        myFrame.setVisible(true);
        
    }
}