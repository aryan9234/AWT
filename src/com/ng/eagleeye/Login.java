package com.ng.eagleeye;

import java.awt.*;
import javax.swing.*;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 02-01-2014
 *
 */

public class Login extends JDialog {

    Login login;
    JComboBox<String> userType;
    JPasswordField passField;
    JButton cancelButton1;
    CardLayout cardLayout;
    JPanel mainPanel,messagePanel,loginPanel;
    JPanel pe;
    JButton button[]=new JButton[3];
    JTextField userField1[]=new JTextField[3];

    Login(EagleEye eagleEye) {

        super(eagleEye,"Agent Verification",true);
        login=this;
        setLocation(400,330);
        setSize(500,300);
        setResizable(false);
        setVisible(true);
    }
}