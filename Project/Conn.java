package Project;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
       //  Class.forName("com.mysql.cj.jdbc.Driver");   //used to register mySQL driver
       try{
       c=DriverManager.getConnection("jdbc:mysql://localhost:3306/EBS", "root", "majorProject");
       s=c.createStatement();
       }catch(Exception e){
        e.printStackTrace();
       }


    }
    
}
