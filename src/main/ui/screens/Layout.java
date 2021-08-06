package ui.screens;

import ui.TypingApplication;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

// note: not actually used in any of the methods!! just for testing!!!

// Layout tutorial from Madsycode on YouTube
// https://www.youtube.com/watch?v=Cxp_HvXZh6g
public class Layout extends JFrame {
    TypingApplication typingApplication;

    public Layout(String title, TypingApplication typingApplication) {
        super(title);
        this.typingApplication = typingApplication;
        this.setSize(typingApplication.getWidth(), typingApplication.getHeight());
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton regularBtn = new JButton("Regular");
        JButton shortBtn = new JButton("Short");
        JButton punctuationBtn = new JButton("Punctuation");
        JButton numberBtn = new JButton("Number");

        JButton viewHistoryBtn = new JButton("View Typing History");
        JButton saveBtn = new JButton("Save Data");
        JButton loadBtn = new JButton("Load Data");
        JButton clearBtn = new JButton("Clear Data");

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8, 6));
        mainContainer.setBackground(Color.YELLOW);

        // panel left
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new LineBorder(Color.BLACK, 3));
        leftPanel.setBackground(Color.orange);
        leftPanel.setLayout(new FlowLayout(4, 4, 4));
        mainContainer.add(leftPanel, BorderLayout.WEST);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 1, 5, 5));
        gridPanel.setBorder(new LineBorder(Color.black, 3));
//        gridPanel.setBackground(Color.red);

        JLabel label = new JLabel("Center Box", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBorder(new LineBorder(Color.black, 3));

        leftPanel.add(gridPanel);
        // put buttons inside the GRID
        gridPanel.add(regularBtn);
        gridPanel.add(shortBtn);
        gridPanel.add(punctuationBtn);
        gridPanel.add(numberBtn);


    }

}
