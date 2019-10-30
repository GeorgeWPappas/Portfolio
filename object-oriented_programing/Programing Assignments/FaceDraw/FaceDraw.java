// Author: George Pappas
// Assignment: Programming Assignment (PA) 3

// Variable declarations
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

class OvalDraw extends Oval {
    // Variable declarations
    private Boolean fillOval;
    public void setfillOval() {
        fillOval = true;
    }

    public OvalDraw() {
        super(0,0,0,0);
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
        fillOval = false;
    }

    public void paintComponent(Graphics g) {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
        if (fillOval) {
            g.setColor(Color.blue);
            g.fillOval(getPositionX()+1, getPositionY()+1, getWidth()-2, getHeight()-2);
            g.setColor(Color.black);
        }
        System.out.format("OvalDraw.paintComponent(x=%d, y=%d, w=%d, h=%d)\n", 
            getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}

class Face extends OvalDraw {
    private OvalDraw eyeLeft;
    private OvalDraw eyeRight;
    private Random rand = new Random();
    private int mouthShape = rand.nextInt(3);

    public Face() {
        super(0,0,0,0);
        eyeLeft = new OvalDraw(0,0,0,0);
        eyeRight = new OvalDraw(100,100,100,100);
    }

    public Face(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
    
        int eyeHeight = heightIn / 7;
        int eyeWidth = eyeHeight / 3;
        int eyePositionX = positionXIn + (widthIn / 2) - (eyeWidth / 2);
        int eyePoisitonY = positionYIn + (heightIn / 3) - (eyeHeight / 2);

        eyeLeft = new OvalDraw(eyePositionX - 10, eyePoisitonY, eyeWidth, eyeHeight);
        eyeLeft.setfillOval();

        eyeRight = new OvalDraw(eyePositionX + 10, eyePoisitonY, eyeWidth, eyeHeight);
        eyeRight.setfillOval();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        eyeLeft.paintComponent(g);
        eyeRight.paintComponent(g);

        if (mouthShape == 0) { // Smile
            g.drawArc(getPositionX(), getPositionY()-(getHeight()/4), getWidth()-10, getHeight()-10, -45, -90);
        } else if (mouthShape == 1) { // Neutral 
            g.drawLine((getPositionX()+getWidth()/4), getPositionY()+(getHeight()/2), (getPositionX()+getWidth()/4) + getWidth()/2, getPositionY()+(getHeight()/2));
        } else if (mouthShape == 2) { // Frown
            g.drawArc(getPositionX(), getPositionY()+(getHeight()/2), getWidth()-10, getHeight()-10, 45, 90);
        }
    }
}

class FacePanel extends JPanel {

    // Variable declarations
    private ArrayList<Face> list;

    public void setList(ArrayList<Face> listIn) {
        list = listIn;
    }

    public FacePanel(ArrayList<Face> listIn) {
        setList(listIn);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Face f : list) {
            f.paintComponent(g);
        }
    }
}

class FaceCount {
    public String toString() {
        return "Generated a Face";
    }
}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw...");

        ArrayList<Face> FaceList = new ArrayList<Face>(); // Creates ArrayList

        // Randomizes position and size of faces
    	Random rand = new Random();
        int FaceNumbers = rand.nextInt(8) + 3;

        for (int i=1; i<=FaceNumbers; i++) { // Starts loop to store random face locations and sizes
        	int positionX = rand.nextInt(700);
        	int PositionY = rand.nextInt(200);
        	int SizeX = rand.nextInt(225) + 75;
        	int SizeY = rand.nextInt(225) + 75;
        	
            Face myFaceDrawFace = new Face(positionX,PositionY,SizeX,SizeY);
            FaceList.add(myFaceDrawFace);

            // Prints out "Generated a Face" when new face is created via toString
            FaceCount myFaceCount = new FaceCount();
            System.out.println(myFaceCount);
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame FaceFrame = new JFrame("FaceDraw");
        FaceFrame.setBounds(100,100,1000,500);
        FaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FacePanel myFaceDrawPanel = new FacePanel(FaceList);
        FaceFrame.add(myFaceDrawPanel);
        FaceFrame.setVisible(true);
    }
}