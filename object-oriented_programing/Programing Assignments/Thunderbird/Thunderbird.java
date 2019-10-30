/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file and the ThunderbirdLite applciation is liscensed under the 
 * BSD-3-Clause.
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 * This application utilizes the HttpRequest.java library developed by 
 * Eric Pogue
 * 
 *****************************************************************************/
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

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

class ContactTile extends JPanel implements MouseListener {
    private ThunderbirdContact contactInSeat = null;

    private Boolean isAnIsle = false;
    public void setIsle() { isAnIsle = true; }

    ContactTile() {
        super();

        // Todo: Remove everything to do with random colors.
        // GWP: I removed everything that has to do with random colors.

        // Todo: Implement visually appealing colors for isles and seats.
        // GWP: I implemented visually appealing colors for the isles and seats.
    }

    ContactTile(ThunderbirdContact contactInSeatIn) {
        super();
        contactInSeat = contactInSeatIn;
        if(contactInSeat != null) {
            addMouseListener(this);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (isAnIsle) {
            g.setColor(new Color(0,0,0)); // This RGB are for the isles.
        } else {
            g.setColor(new Color(23,95,210)); // This RGB are for the seats.
        }
        
        g.fillRect (10, 10, panelWidth-20, panelHeight-20);

        g.setColor(new Color(146,255,59)); // This RGB are for the names.

        final int fontSize=18;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-60;
        int stringY = (panelHeight/2)+30;
        if (contactInSeat != null) {

            // ToDo: Dispay preferred name instead of first and last name.
            // GWP: I have changed the code so that the preferred name is displayed instead of the first and last name.
            String printedName = contactInSeat.getPreferredName();
            g.drawString(printedName,stringX,stringY);
        }
    }

    public void mousePressed(MouseEvent me) {
        JOptionPane.showMessageDialog(new JFrame(), contactInSeat, contactInSeat.getPreferredName(), JOptionPane.PLAIN_MESSAGE);
    }

    public void mouseReleased(MouseEvent me) {}
    public void mouseClicked(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
}

class ThunderbirdFrame extends JFrame implements ActionListener {
    private ArrayList<ContactTile> tileList;
    int test = 0;

    public ThunderbirdFrame() {
        setBounds(200,200,1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JPanel contactGridPanel = new JPanel();
        contentPane.add(contactGridPanel, BorderLayout.CENTER);
        contactGridPanel.setLayout(new GridLayout(4,8));        

        JButton reverseView = new JButton("Reverse View");
        buttonPanel.add(reverseView);
        reverseView.addActionListener(this);

        ThunderbirdModel tbM = new ThunderbirdModel();
        tbM.LoadIndex();
        tbM.LoadContactsThreaded();

        // Todo: Review ThunderbirdModel in detail and implement a multithreaded version of loading contacts. 
        // Hint: Review LoadContact() and LoadContactsThreaded() in detail.
        // GWP: I have implemented the multithreaded version of loading contacts.

        System.out.println("Printing Model:");
        System.out.println(tbM);
        tbM.ValidateContacts();

        tileList = new ArrayList<ContactTile>();
        if ((test%2) == 0) {
            for (int i=1; i<33; i++) {
                ThunderbirdContact contactInSeat = tbM.findContactInSeat(i);
                if (contactInSeat != null) {
                    System.out.println(contactInSeat);
                }

                ContactTile tile = new ContactTile(contactInSeat);

                // Todo: Place all the isle seats in a array or an ArrayList instead of hard coding them.
                // GWP: Cound not place all the isle seats in a array.
                if ((i==4)||(i==12)||(i==20)||(i==28)||(i==31)) {
                    tile.setIsle();
                }

                tileList.add(tile);
                contactGridPanel.add(tile);
            }
        } else {
            for (int i=32; i>0; i--) {
                ThunderbirdContact contactInSeat = tbM.findContactInSeat(i);
                if (contactInSeat != null) {
                    System.out.println(contactInSeat);
                }

                ContactTile tile = new ContactTile(contactInSeat);

                if ((i==4)||(i==12)||(i==20)||(i==28)||(i==31)) {
                    tile.setIsle();
                }

                tileList.add(tile);
                contactGridPanel.add(tile);
            }
        }        
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("Reversing");
        test++;

        for (ContactTile tile : tileList) {
            // Todo: Remove randomization functionality and implement a visually appealing view of seats and isles.
            // GWP: Removed randomization function and made visually appealing.

            // Todo: Implement reverse view where it looks like you are looking at the room from the back instead of the front 
            //     of the room.
            // GWP: Could not implement reverse view.

        }
        repaint();
    }
}

// Todo: Rename the following class to Thunderbird.
// Hint: This will also require you to rename the Java file.
// GWP: I have renamed the following class to Thunderbird.
public class Thunderbird {
    public static void main(String[] args) {

        // Todo: Update the following line so that it reflects the name change to Thunderbird.
        // GWP: I have updated the following line so that it reflects the name change to Thunderbird.
        System.out.println("Thunderbird Starting...");

        ThunderbirdFrame myThunderbirdFrame = new ThunderbirdFrame();
        myThunderbirdFrame.setVisible(true);
    }
}