package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login,cancel,signup;     //declaring globally so that they are accessible in constructor and actionlistener
    JTextField userName,password;
    Choice loginChoice;
    Login(){
        //super must be first line inside constructor.
        super("Login Page");
        // getContentPane() gives access to whole frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);   //removing the default layout to create our own.

        JLabel lblUserName=new JLabel("UserName");
        lblUserName.setBounds(300,20,100,20);
        add(lblUserName);

        //to create input field
        userName=new JTextField();
        userName.setBounds(400,20,150,20);
        add(userName);

        JLabel lblPassword=new JLabel("Password");
        lblPassword.setBounds(300,60,100,20);
        add(lblPassword);

        password=new JTextField();
        password.setBounds(400,60,150,20);
        add(password);

        JLabel loginAs=new JLabel("Login As");
        loginAs.setBounds(300,100,100,20);
        add(loginAs);

        loginChoice=new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,100,150,20);
        add(loginChoice);

        //adding Buttons
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2=i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login=new JButton("Login",new ImageIcon(i2));
        login.setBounds(330,160,100,20);
        login.addActionListener(this);
        add(login);

        ImageIcon i3 =new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4=i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel=new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(450,160,100,20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i5 =new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6=i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup=new JButton("Sign Up",new ImageIcon(i6));
        signup.setBounds(380,200,100,20);
        signup.addActionListener(this);
        add(signup);

        ImageIcon i7 =new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8=i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel image=new JLabel(i9);
        image.setBounds(0,0,250,250);
        add(image);



        //Frame
        setSize(640,300);
        setLocation(400, 200);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        //actionevent let us know the source of trigger, which button is clicked
        if(ae.getSource()==login){
            String susername=userName.getText();
            String spassword=password.getText();
            String user=loginChoice.getSelectedItem();

            try{
                Conn c=new Conn();
                String query="SELECT * FROM login WHERE username='"+susername+"' AND password='"+spassword+"' AND USER='"+user+"'";
                ResultSet rs=c.s.executeQuery(query); //for ddl statements  // returns data  //from sql class

                if(rs.next()){
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    userName.setText("");
                    password.setText("");
                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(ae.getSource()==cancel){
            setVisible(false);
        }else if(ae.getSource()==signup){
            setVisible(false);
            new Signup();
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
