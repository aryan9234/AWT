package com.ng.techon;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 16-03-2014
 *
 */

class Explorer extends Thread implements TreeSelectionListener,ActionListener {

    private Explorer ex;
    private DefaultMutableTreeNode root;
    private JTree jt;
    JScrollPane jp;
    private JPanel treePanel=new JPanel();

    private String s6="";
    private JButton bg[];
    //JButton drive[]=new JButton[3];
    //JButton focusButton=null;
    private JTextField address=new JTextField(15);

    Explorer() {

        this.ex=this;
        root=new DefaultMutableTreeNode("My Computer");

        //centerPanel=new JPanel(new GridLayout(40,5,10,10));
        //address.setEditable(false);

        //back=new JButton(new ImageIcon(Explorer.class.getResource("images/arrow_left24.png")));
        //back.setEnabled(false);
        //back.addActionListener(this);
        //c=new CenterPopup(this);
        //start();
        addNode();

        //t.start();
        jt=new JTree(root);
        //DefaultTreeCellRenderer d=(DefaultTreeCellRenderer)jt.getCellRenderer();
        //jt.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jt.addTreeSelectionListener(this);
        treePanel.add(jt);
        jp=new JScrollPane(treePanel);
    }

    public void run() {
    }

    private void addNode() {

        File f[]=File.listRoots();
        DefaultMutableTreeNode d[]=new DefaultMutableTreeNode[f.length];
        for(int i=0;i<3;i++){
            d[i]=new DefaultMutableTreeNode(f[i].getPath());
            root.add(d[i]);
            String sr[]=f[i].list();
            DefaultMutableTreeNode b[]=new DefaultMutableTreeNode[sr.length];
            for(int k=0;k<sr.length;k++){
                File f6=new File(f[i].getPath()+sr[k]);
                if(f6.isDirectory()){
                    if(!f6.isHidden()){
                        b[k]=new DefaultMutableTreeNode(f6.getName());
                        //new Thread(){
                        //Thread.currentThread().start();

                        d[i].add(b[k]);
                        if((!(f6.getName()).equals("Documents and Settings"))&&(!(f6.getName()).equals("PerfLogs"))&&(!(f6.getName()).equals("Windows"))&&(!(f6.getName()).equals("Users")))
                            rec(f6,b[k]);
                    }
                }
            }
        }
    }

    private void computer() {
        //address.setText("My Computer");
        File f[]=File.listRoots();
        //centerPanel.setVisible(false);
        //centerPanel.removeAll();
        for(int i=0;i<3;i++){
            //drive[i]=new JButton(f[i].getPath().toString());
            //drive[i].setBackground(ibi.ibiColor);
            //drive[i].addMouseListener(c);
            //drive[i].addActionListener(c);
            //drive[i].addFocusListener(c);
            //centerPanel.add(drive[i]);
        }
        //centerPanel.setVisible(true);
    }

    private void rec(File d3,DefaultMutableTreeNode r1) {
        int k=0;
        File f6;
        String u[]=d3.list();
        DefaultMutableTreeNode e[]=new DefaultMutableTreeNode[u.length];
        for(k=0;k<u.length-1;k++) {
            try{
                f6=new File(d3.getPath()+File.separator+u[k]);
                if(f6.isDirectory()){
                    if(!f6.isHidden()){
                        e[k]=new DefaultMutableTreeNode(f6.getName());
                        r1.add(e[k]);
                        rec(f6,e[k]);
                    }
                }
            }catch(Exception e1) {
                System.out.println(e1);
            }
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        if(!(e.getPath().getLastPathComponent().toString()).equals("My Computer")) {
            //s6=e.getPath().toString();
            if(!s6.equals(""))
                address.setText(s6);

            TreePath t=e.getPath();
            Object o[]=t.getPath();

            for(int i=1;i<o.length;i++){
                if(i==1)
                    s6=o[i].toString();
                if(i==2)
                    s6=s6+o[i].toString();
                if(i>2)
                    s6=s6+File.separator+o[i].toString();
            }
            File fc=new File(s6);
            newIcon(fc);
        }
    }

    public void newIcon(File fc) {

        //ibi.main.newFolder.setEnabled(true);
        address.setText(s6);
        //back.setEnabled(true);
        //centerPanel.setVisible(false);
        String stre[]=fc.list();
        bg=new JButton[stre.length];
        //centerPanel.removeAll();
        for(int i=0;i<stre.length;i++){
            //System.out.println(stre[i]);
            bg[i]=new JButton(stre[i]);
            bg[i].setSize(200,200);
            //bg[i].setBackground(ibi.ibiColor);
            bg[i].addActionListener(this);
            //bg[i].addMouseListener(c);
            //bg[i].addActionListener(c);
            //bg[i].addFocusListener(c);
            //centerPanel.add(bg[i]);
        }
        //centerPanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

   /*     if(e.getSource()==back){
            int x=0;
            int g=0;
            byte b[]=s6.getBytes();
            for(int i=0;i<b.length;i++){
                if(b[i]==92){
                    x=i;
                    g++;
                }
            }
            if((s6.equals("C:"))||(s6.equals("D:"))||(s6.equals("E:"))){
                computer();
                back.setEnabled(false);
                ibi.main.paste.setEnabled(false);
                ibi.main.newFolder.setEnabled(false);
            }
            else {
                String df=s6;
                s6=s6.substring(0,x);
                address.setText(s6);

                if((s6.equals("C:"))||(s6.equals("D:"))||(s6.equals("E:"))){
                    if((df.equals("C:"+File.separator))||(df.equals("D:"+File.separator))||(df.equals("E:"+File.separator))){
                        computer();
                        back.setEnabled(false);
                        ibi.main.paste.setEnabled(false);
                        ibi.main.newFolder.setEnabled(false);
                    }
                    else{
                        newIcon(new File(s6));
                    }
                }
                else
                    newIcon(new File(s6));
            }
        }*/
    }
}