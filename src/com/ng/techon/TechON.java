package com.ng.techon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 16-03-2014
 *
 */
public class TechON extends JFrame implements ActionListener {

    static TechON techon;
    private JMenu menu[]=new JMenu[5];
    private JButton button[]=new JButton[5];
    private JCheckBoxMenuItem checkbox[]=new JCheckBoxMenuItem[2];
    private JPopupMenu pop[]=new JPopupMenu[5];
    private JMenuBar menubar;
    private JPanel navigationPanel;
    Explorer e=new Explorer();

    private JLabel imageArray[]=new JLabel[2];

    private TechON(String label) {

        super(label);
        this.techon=this;

        JPanel dataPanel=new JPanel(new GridLayout(2,1));

        JPanel addressPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        addressPanel.setBackground(Color.gray);
        addressPanel.add(new JLabel(new ImageIcon(TechON.class.getResource("images/arrow_left24.png"))));
        addressPanel.add(new JLabel(new ImageIcon(TechON.class.getResource("images/arrow_right24.png"))));
        addressPanel.add(new JTextField(15));

        JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.gray);
        String buttonCaption[]={"Organize","System properties","PD-SPY"};
        for(int i=0;i<3;i++){
            buttonPanel.add(button[i]=new JButton(buttonCaption[i]));
            button[i].addActionListener(this);}

            dataPanel.add(addressPanel);
            dataPanel.add(buttonPanel);

            add(dataPanel,BorderLayout.NORTH);

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println(e);
        }


        menubar=new JMenuBar();
        String menuCaption[]={"File","Edit","View","Tools","Help"};
        for(int i=0;i<5;i++){
            menu[i]=new JMenu(menuCaption[i]);
            menubar.add(menu[i]);
        }

        pop[0]=new JPopupMenu();
        JMenu menu1=new JMenu("Layout");
        String checkBoxCaption[]={"MenuBar","Navigation pane"};
        for(int i=0;i<2;i++){
            checkbox[i]=new JCheckBoxMenuItem(checkBoxCaption[i]);
            checkbox[i].addActionListener(this);
            menu1.add(checkbox[i]);
        }
        pop[0].add(menu1);
        JMenuItem menuItem;
        pop[0].add(menuItem=new JMenuItem("Close"));
        menuItem.addActionListener(this);
    }

    private TechON() {

        this("TechON");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(getToolkit().getScreenSize().width/4-200,getToolkit().getScreenSize().height/4-100,getToolkit().getScreenSize().width/2+400,getToolkit().getScreenSize().height/2+200);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button[0]) {
            showPopup(button[0]);
        }

        if(e.getSource()==checkbox[0]){
            checkMenuStatus();
        }
        if(e.getSource()==checkbox[1]){
            checkNavigationStatus();
        }
        if(e.getActionCommand().equals("Close"))
            System.exit(0);
    }

    private void checkMenuStatus() {

        if(checkbox[0].getState()==true)
            techon.setJMenuBar(this.menubar);
        else
            techon.setJMenuBar(null);
            techon.validate();
    }

    private void checkNavigationStatus() {

        if(checkbox[1].getState()==true)
            techon.add(e.jp,BorderLayout.WEST);
        else
            techon.remove(e.jp);
        techon.validate();
        techon.repaint();
    }

    private void showPopup(JButton button) {
            pop[0].show(button,button.getLocation().x-10,button.getLocation().y+25);
    }

    public static void main(String...s) {
        new TechON();
    }
}