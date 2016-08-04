package com.nayragames.encode;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * (c) 2013 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 01-12-2013
 *
 */

public class ENC extends JFrame implements ActionListener {

    private JTextArea textArea[]=new JTextArea[2];
    private JButton encButton;

    private ENC(String title) {

        super(title);
        setLayout(new GridLayout(6,1));
        add(new JScrollPane(textArea[0]=new JTextArea()));
        JPanel buttonPanel=new JPanel();
        buttonPanel.add(encButton=new JButton("ENC"));
        encButton.addActionListener(this);
        add(buttonPanel);
        add(new JScrollPane(textArea[1]=new JTextArea()));
    }

    private ENC() {
        this("ENC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(getToolkit().getScreenSize().width/4,getToolkit().getScreenSize().height/4,getToolkit().getScreenSize().width/2,getToolkit().getScreenSize().height/2);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
{
encrypt();
}

    public static void main(String...s) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ENC app = new ENC();
            }
            });
    }

    private void encrypt() {
        textArea[1].setText(null);
        textArea[1].append(textArea[0].getText());
    }
}