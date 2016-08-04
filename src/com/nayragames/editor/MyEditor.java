package com.nayragames.editor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;

/**
 * (c) 2013 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 17-12-2013
 *
 */

public class MyEditor extends JFrame implements ActionListener {

    private MyEditor myEditor;
    private JTextField textfield;
    private JTextArea textArea;
    private JPanel panel[]=new JPanel[2];
    private JButton button[]=new JButton[2];

    private MyEditor(String label) {

        super(label);
        this.myEditor=this;
        String buttonCaption[]={"Compile","Execute"};
        for(int i=0;i<2;i++){
            panel[i]=new JPanel();
            panel[i].setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
            button[i]=new JButton(buttonCaption[i]);
            button[i].addActionListener(this);
        }
        panel[0].add(new JLabel("ENTER FILE NAME :"));
        panel[0].add(textfield=new JTextField(15));
        add(panel[0],BorderLayout.NORTH);
        add(new JScrollPane(textArea=new JTextArea()),BorderLayout.CENTER);
        textArea.setEditable(false);
        panel[1].add(button[0]);
        panel[1].add(button[1]);
        add(panel[1],BorderLayout.SOUTH);
    }

    private MyEditor() {
        this("MyEditor");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(getToolkit().getScreenSize().width/4,getToolkit().getScreenSize().height/4,getToolkit().getScreenSize().width/2,getToolkit().getScreenSize().height/2);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button[0])
            compile();
        if(e.getSource()==button[1])
            execute();
    }

    private void compile() {
        if(!textfield.getText().trim().equals("")){
            Runtime rt=Runtime.getRuntime();
            try{
                Process p=rt.exec("C://Program Files (x86)/Java/jdk1.7.0_02/bin/javac -d . "+textfield.getText()+".java");
                output(p);
                if(new File(textfield.getText()+".class").exists())
                    JOptionPane.showMessageDialog(myEditor,"Compiled Successfully","MyEditor",JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(myEditor,"Problem in Compiletation","MyEditor",JOptionPane.ERROR_MESSAGE);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else
            JOptionPane.showMessageDialog(myEditor,"Enter File Name First Then Compile","MyEditor",JOptionPane.WARNING_MESSAGE);
    }

    private void execute() {

        if(new File(textfield.getText()+".class").exists()){
            Runtime rt=Runtime.getRuntime();
            try{
                Process p=rt.exec("C://Program Files (x86)/Java/jdk1.7.0_02/bin/java "+textfield.getText()+"");
                output(p);
                }catch(Exception e){
                    System.out.println(e);
                }
            }else
                JOptionPane.showMessageDialog(myEditor,"First Compile Then Execute","MyEditor",JOptionPane.ERROR_MESSAGE);
    }

    private void output(Process p) {

        try{
            //DataInputStream df=new DataInputStream(p.getInputStream());                  // df.readLine() is deprecated so i m using bf
            BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
            textArea.setText(null);
            String s1="";
            while(s1!=null){
                s1=bf.readLine();
                if(s1!=null)
                    textArea.append(s1+"\n");}
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String...s) {
        new MyEditor();
    }
}