package com.ng.editor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 17-03-2014
 *
 */

public class PersonalEditor extends JFrame implements ActionListener {

    private PersonalEditor personalEditor;
    private JTextField textfield;
    private JTextArea textArea,textArea1;
    private JPanel panel[]=new JPanel[2];
    private JButton button[]=new JButton[2];

    private PersonalEditor(String label) {

        super(label);
        this.personalEditor=this;
        String buttonCaption[]={"About","Execute"};
        for(int i=0;i<2;i++){
            panel[i]=new JPanel();
            panel[i].setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
            button[i]=new JButton(buttonCaption[i]);
            button[i].addActionListener(this);
        }

        panel[0].add(new JLabel("ENTER CLASS NAME :"));
        panel[0].add(textfield=new JTextField(15));
        //add(panel[0],BorderLayout.NORTH);
        JPanel headerPanel=new JPanel();
        headerPanel.add(new JLabel("                CODE HERE__Java Program                               "));
        headerPanel.add(new JLabel("                                                        OUTPUT"));
        add(headerPanel,BorderLayout.NORTH);
        JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(textArea=new JTextArea()),new JScrollPane(textArea1=new JTextArea()));
        textArea1.setEditable(false);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(500);
        add(splitPane,BorderLayout.CENTER);
        panel[1].add(button[0]);
        panel[1].add(button[1]);
        add(panel[1],BorderLayout.SOUTH);
    }

    private PersonalEditor() {
        this("PersonalEditor");
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
        if(e.getSource()==button[0]){
            JOptionPane.showMessageDialog(personalEditor,"Coded By Abhishek Aryan\nContact No. +918427440232\nEmail-aryan9234@gmail.com","PersonalEditor",JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource()==button[1]){
            textfield.setText(getClassName());
            if(new File(textfield.getText().trim()+".java").exists())
                JOptionPane.showMessageDialog(personalEditor,"Already Exist Class","PersonalEditor",JOptionPane.ERROR_MESSAGE);
            else{
                createFile();
                compile();
                execute();
            }
        }
    }

    private String getClassName() {

        String className="";
        Scanner sc=new Scanner(textArea.getText());
        while(sc.hasNext()){
            if(sc.next().equals("class"))
                className=sc.next();
        }
        return className;
    }

    private void createFile() {

        PrintStream p=null;
        FileOutputStream fo;
        try{
                p=new PrintStream(fo=new FileOutputStream(textfield.getText()+".java"));
                StringTokenizer str=new StringTokenizer(textArea.getText(),"\n");
                while(str.hasMoreTokens()){
                    p.println(str.nextToken());
                }
                fo.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void compile() {
        try{
                Runtime rt=Runtime.getRuntime();
                System.out.println(System.getProperty("java.home"));

                Process p=rt.exec("1.7.0_03/bin/javac -d . "+textfield.getText().trim()+".java");
                Thread.sleep(3000);
                output(p);
            }catch(Exception e){
                System.out.println(e);
            }
        if(new File(textfield.getText().trim()+".class").exists()==false)
            JOptionPane.showMessageDialog(personalEditor,"Compilation Problem","PersonalEditor",JOptionPane.ERROR_MESSAGE);
    }

    private void execute() {

        Runtime rt=Runtime.getRuntime();
        try{
            Process p=rt.exec("C://Program Files/Java/jdk1.7.0_03/bin/java "+textfield.getText()+"");
            output(p);
           }catch(Exception e){
                System.out.println(e);
           }
           delete();
    }

    private void output(Process p) {
        try{
            //DataInputStream df=new DataInputStream(p.getInputStream());                  // df.readLine() is deprecated so i m using bf
            BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
            textArea1.setText(null);
            String s1="";
            while(s1!=null){
                s1=bf.readLine();
                if(s1!=null)
                    textArea1.append(s1+"\n");}
            }catch(Exception e){
                System.out.println(e);
            }
    }

    private void delete() {

        if(new File(textfield.getText().trim()+".class").exists())
            new File(textfield.getText().trim()+".class").delete();
        if(new File(textfield.getText().trim()+".java").exists())
            new File(textfield.getText().trim()+".java").delete();
    }

    public static void main(String...s) {
        new PersonalEditor();
    }
}