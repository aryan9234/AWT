package com.nayragames.copytable;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

/**
 * (c) 2013 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2013
 *
 */

public class CopyTable extends JFrame implements ActionListener {

    private CopyTable copyTable;
    private JButton button[]=new JButton[10];
    private JTextField field[]=new JTextField[3];
    private JComboBox tableCombo,table2Combo;
    private JList columnList,selectedList;
    private JTextField userpass[]=new JTextField[4];
    private DefaultListModel model=new DefaultListModel();
    private DefaultListModel model1=new DefaultListModel();
    private JButton clearButton,clearButton1;

    private CopyTable() {

        super("AATECH.");
        this.copyTable=this;
        JPanel panel[]=new JPanel[7];
        JLabel headerLabel=new JLabel("<html><h2><u>COPY YOUR TABLE FROM ANY DSN</u></h2></html>",JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        panel[1]=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel[1].add(new JLabel("ENTER DSN NAME :"));
        panel[1].add(field[0]=new JTextField("DBMStudio",12));
        panel[1].add(new JLabel("USER ID :"));
        panel[1].add(userpass[0]=new JTextField("root",12));
        panel[1].add(new JLabel("PASS :"));
        panel[1].add(userpass[1]=new JTextField(10));
        panel[1].add(button[0]=new JButton("Search"));
        panel[1].add(clearButton=new JButton("Clear"));
        button[0].addActionListener(this);
        clearButton.addActionListener(this);

        panel[0]=new JPanel(new BorderLayout());
        panel[0].add(headerLabel,BorderLayout.NORTH);
        panel[0].add(panel[1],BorderLayout.CENTER);
        add(panel[0],BorderLayout.NORTH);

        panel[3]=new JPanel();
        panel[3].add(new JLabel("SELECT TABLE :"));
        panel[3].add(tableCombo=new JComboBox());
        tableCombo.setPreferredSize(new Dimension(150,25));
        panel[3].add(button[1]=new JButton("Refresh"));
        button[1].addActionListener(this);
        panel[3].setBorder(BorderFactory.createEmptyBorder(0,0,0,400));


        panel[2]=new JPanel(new BorderLayout());
        panel[2].add(panel[3],BorderLayout.NORTH);

        JScrollPane jscolumn=new JScrollPane(columnList=new JList(model));
        jscolumn.setBorder(BorderFactory.createEmptyBorder(20,100,0,0));
        columnList.setFixedCellWidth(50);
        columnList.setBackground(null);

        JScrollPane jsselected=new JScrollPane(selectedList=new JList(model1));
        jsselected.setBorder(BorderFactory.createEmptyBorder(20,0,0,100));
        selectedList.setFixedCellWidth(50);
        selectedList.setBackground(null);

        panel[5]=new JPanel(new GridLayout(5,1));
        panel[5].add(button[2]=new JButton(">"));
        button[2].addActionListener(this);
        panel[5].add(button[3]=new JButton(">>"));
        button[3].addActionListener(this);
        panel[5].add(button[4]=new JButton("<"));
        button[4].addActionListener(this);
        panel[5].add(button[5]=new JButton("<<"));
        button[5].addActionListener(this);
        panel[5].setBorder(BorderFactory.createEmptyBorder(10,0,0,30));

        panel[4]=new JPanel(new GridLayout(1,3));
        panel[4].add(jscolumn);
        panel[4].add(panel[5]);
        panel[4].add(jsselected);
        panel[2].add(panel[4],BorderLayout.CENTER);

        JPanel dataPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        dataPanel.add(new JLabel("ENTER DSN NAME :"));
        dataPanel.add(field[1]=new JTextField(12));
        dataPanel.add(new JLabel("USER ID :"));
        dataPanel.add(userpass[2]=new JTextField("root",12));
        dataPanel.add(new JLabel("PASS :"));
        dataPanel.add(userpass[3]=new JTextField(10));
        dataPanel.add(button[6]=new JButton("Search"));
        dataPanel.add(clearButton1=new JButton("Clear"));
        button[6].addActionListener(this);
        clearButton1.addActionListener(this);

        JPanel radioPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(new JLabel("TABLE IN DSN :"));
        radioPanel.add(table2Combo=new JComboBox());
        table2Combo.setPreferredSize(new Dimension(165,25));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JPanel newPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        newPanel.add(new JLabel("NEW TABLE NAME :"));
        newPanel.add(field[2]=new JTextField(15));
        newPanel.add(button[7]=new JButton("Create"));
        button[7].addActionListener(this);
        newPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JPanel query=new JPanel(new GridLayout(5,1));
        query.add(dataPanel);
        query.add(radioPanel);
        query.add(newPanel);

        JPanel extend=new JPanel(new GridLayout(2,1));
        extend.add(panel[2]);
        extend.add(query);
        add(extend,BorderLayout.CENTER);

        panel[6]=new JPanel();
        panel[6].add(button[8]=new JButton("About"));
        panel[6].add(button[9]=new JButton("Exit"));
        button[8].addActionListener(this);
        button[9].addActionListener(this);
        add(panel[6],BorderLayout.SOUTH);

        try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(getToolkit().getScreenSize().width/5,getToolkit().getScreenSize().height/5-50,getToolkit().getScreenSize().width/2+100,getToolkit().getScreenSize().height/2+175);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button[0]){
            edit(new JTextField[]{field[0],userpass[0],userpass[1]});
            searchTable(tableCombo,new JTextField[]{field[0],userpass[0],userpass[1]});}

            if(e.getSource()==button[1]){
                model.clear();
                model1.clear();
                searchColumn();
            }

        if(e.getSource()==button[2]){
            if(model.getSize()==0)
                JOptionPane.showMessageDialog(copyTable,"Nothing for Selection","AATECH.",JOptionPane.WARNING_MESSAGE);
            else{
                if(columnList.isSelectionEmpty())
                    JOptionPane.showMessageDialog(copyTable,"Select Column Which You Want to Use","AATECH.",JOptionPane.WARNING_MESSAGE);
                else{
                    model1.addElement(model.getElementAt(columnList.getSelectedIndex()));
                    model.removeElementAt(columnList.getSelectedIndex());
                }
            }
        }

        if(e.getSource()==button[3]){
            for(int i=model.getSize()-1;i>=0;i--){
                    model1.addElement(model.getElementAt(i));
                    model.removeElementAt(i);
            }
        }

        if(e.getSource()==button[4]){
            if(model1.getSize()==0)
                JOptionPane.showMessageDialog(copyTable,"Nothing for Remove","AATECH.",JOptionPane.WARNING_MESSAGE);
            else{
                if(selectedList.isSelectionEmpty())
                        JOptionPane.showMessageDialog(copyTable,"Select Column Which You Want to Remove","AATECH.",JOptionPane.WARNING_MESSAGE);
                else{
                    model.addElement(model1.getElementAt(selectedList.getSelectedIndex()));
                    model1.removeElementAt(selectedList.getSelectedIndex());
                }
            }
        }

        if(e.getSource()==button[5]){
            //model.clear();
            for(int i=model1.getSize()-1;i>=0;i--){
                model.addElement(model1.getElementAt(i));
                model1.removeElementAt(i);
            }
        }

        if(e.getSource()==button[6]){
            edit(new JTextField[]{field[1],userpass[2],userpass[3]});
            searchTable(table2Combo,new JTextField[]{field[1],userpass[2],userpass[3]});
        }

        if(e.getSource()==button[7]){
            if(field[2].getText().trim().equals(""))
                JOptionPane.showMessageDialog(copyTable,"Enter Name of Table","AATECH.",JOptionPane.WARNING_MESSAGE);
        else{
                boolean b=false;
                for(int i=0;i<table2Combo.getItemCount();i++)
                    if(field[2].getText().trim().equals(table2Combo.getItemAt(i).toString()))
                        b=true;
                    if(b==true)
                        JOptionPane.showMessageDialog(copyTable,"Table Already Exist In Your DSN","AATECH.",JOptionPane.WARNING_MESSAGE);
                    else
                        create();
            }
        }

        if(e.getSource()==button[8])
            JOptionPane.showMessageDialog(copyTable,"Coded By Abhishek Aryan"+"\n"+"Contact No. +918427440232"+"\n"+"Email-aryan9234@gmail.com","AATECH.",JOptionPane.INFORMATION_MESSAGE);
        if(e.getSource()==button[9])
            System.exit(0);

        if(e.getSource()==clearButton){
            tableCombo.removeAllItems();
            clear(new JTextField[]{field[0],userpass[0],userpass[1]});
        }

        if(e.getSource()==clearButton1){
            table2Combo.removeAllItems();
            clear(new JTextField[]{field[1],userpass[2],userpass[3]});
        }

    }


    private void clear(JTextField[] clearField){

        for(int i=0;i<clearField.length;i++)
            clearField[i].setEditable(true);
    }

    private void edit(JTextField[] editField){
        for(int i=0;i<editField.length;i++)
            editField[i].setEditable(false);
    }

    private void fieldComboData() {

        table2Combo.removeAllItems();
        for(int i=model1.getSize()-1;i>=00;i--)
            table2Combo.addItem(model1.getElementAt(i));
    }


    private void searchTable(JComboBox tableCombo,JTextField field[]) {

        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection c=DriverManager.getConnection("jdbc:odbc:"+field[0].getText(),field[1].getText(),field[2].getText());
            DatabaseMetaData dbmd=c.getMetaData();
            ResultSet rs=dbmd.getTables(null,null,null,new String[]{"Table"});
            tableCombo.removeAllItems();
            while(rs.next()) {
                tableCombo.addItem(rs.getString(3));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(copyTable,"Check Your Connection Info.","AATECH.",JOptionPane.ERROR_MESSAGE);
            clear(new JTextField[]{field[0],field[1],field[2]});
            tableCombo.removeAllItems();
        }
        catch(Exception i) {
            System.out.println(i);
        }
    }

    private void searchColumn() {

        try
        {

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection c=DriverManager.getConnection("jdbc:odbc:"+field[0].getText(),userpass[0].getText(),userpass[1].getText());
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("select * from "+tableCombo.getSelectedItem().toString()+"");
            ResultSetMetaData rsmd=rs.getMetaData();
            model.clear();
            for(int i=1;i<rsmd.getColumnCount()+1;i++) {
                model.addElement(rsmd.getColumnName(i).toUpperCase());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }catch(Exception i) {}
    }

    private void create() {

        String sql="create table "+field[2].getText()+"(";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection c=DriverManager.getConnection("jdbc:odbc:"+field[0].getText()+"",userpass[0].getText(),userpass[1].getText());
            Connection c1=DriverManager.getConnection("jdbc:odbc:"+field[1].getText()+"",userpass[2].getText(),userpass[3].getText());
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection c1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","aryan");
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Statement s1=c1.createStatement();
            String query="select ";
            for(int i=0;i<model1.size();i++){
                query=query+model1.getElementAt(i).toString();
                if(i<model1.size()-1)
                    query=query+",";
            }
            query=query+" from "+tableCombo.getSelectedItem().toString();
            System.out.println(query);
            ResultSet result=s.executeQuery(query);
            ResultSetMetaData rsmd=result.getMetaData();
            for(int i=1;i<rsmd.getColumnCount()+1;i++){
                sql=sql+rsmd.getColumnName(i)+" varchar(255)";
                if(i<rsmd.getColumnCount())
                    sql=sql+",";
            }
            sql=sql+")";
            s1.executeUpdate(sql);
                while(result.next()){
                    String insertsql="insert into "+field[2].getText()+" values(";
                    for(int i=1;i<rsmd.getColumnCount()+1;i++){
                        insertsql=insertsql+"'"+result.getString(i)+"'";
                        if(i<rsmd.getColumnCount())
                            insertsql=insertsql+",";
                    }
                    insertsql=insertsql+")";
                    s1.executeUpdate(insertsql);
                }
                JOptionPane.showMessageDialog(copyTable,field[2].getText()+" Table Created","AATECH.",JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(copyTable,"SQL Exception","AATECH.",JOptionPane.ERROR_MESSAGE);
            }catch(Exception i){
                System.out.println(i);
            }
    }

    public static void main(String...s) {
        new CopyTable();
    }
}
