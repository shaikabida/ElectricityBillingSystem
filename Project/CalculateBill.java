package Project;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    JTextField tfaddress,tfunits;
    JButton submit,cancel;
    JLabel lblname,labelAddress;
    Choice meterNumber,cmonth;
    CalculateBill(){
        setBounds(400,150,700,500);
        

        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading=new JLabel("Calculate Electricity Bill");
        heading.setBounds(100,10,400,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);

        JLabel lblmeternumber=new JLabel("Meter Number");
        lblmeternumber.setBounds(100,80,100,20);
        p.add(lblmeternumber);

        meterNumber=new Choice();
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("SELECT * FROM customer");
            while(rs.next()){
                meterNumber.add(rs.getString("meter_no"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        meterNumber.setBounds(240,80,200,20);
        p.add(meterNumber);


        JLabel lblmeterno=new JLabel("Name");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);

        lblname=new JLabel("");
        lblname.setBounds(240,120,100,20);
        p.add(lblname);

        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);

        labelAddress=new JLabel();
        labelAddress.setBounds(240,160,200,20);
        p.add(labelAddress);

        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("SELECT * FROM customer WHERE meter_no='"+meterNumber.getSelectedItem()+"'");
            while(rs.next()){
                lblname.setText(rs.getString("name"));
                labelAddress.setText(rs.getString("address"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        meterNumber.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                try{
                    Conn c=new Conn();
                    ResultSet rs=c.s.executeQuery("SELECT * FROM customer WHERE meter_no='"+meterNumber.getSelectedItem()+"'");
                    while(rs.next()){
                        lblname.setText(rs.getString("name"));
                        labelAddress.setText(rs.getString("address"));
                    }
        
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        });

        JLabel lblcity=new JLabel("Units Consumed");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        tfunits=new JTextField();
        tfunits.setBounds(240,200,200,20);
        p.add(tfunits);

        JLabel lblstate=new JLabel("Month");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        cmonth=new Choice();
        cmonth.setBounds(240,240,200,20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        p.add(cmonth);

        submit=new JButton("Submit");
        submit.setBounds(120,350,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);

        cancel=new JButton("Cancel");
        cancel.setBounds(250,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        setLayout(new BorderLayout());
        add(p,"Center");

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            String meter=meterNumber.getSelectedItem();
            String units=tfunits.getText();
            String month=cmonth.getSelectedItem();

            int totalBill=0;
            int units_consumed=Integer.parseInt(units);
            String query="SELECT * FROM tax";

            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery(query);
                while(rs.next()){
                    totalBill+=units_consumed*Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill+=Integer.parseInt(rs.getString("meter_rent"));
                    totalBill+=Integer.parseInt(rs.getString("service_charge"));
                    totalBill+=Integer.parseInt(rs.getString("service_tax"));
                    totalBill+=Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill+=Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            String query2="INSERT INTO bill VALUES ('"+meter+"','"+month+"','"+units+"','"+totalBill+"','NOT PAID')";
            try{
                Conn c=new Conn();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Succesfully");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(ae.getSource()==cancel){
            setVisible(false);
        }

    }
    public static void main(String[] args){
        new CalculateBill();
    }
    
}
