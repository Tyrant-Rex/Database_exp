package sample.sql_operation;

import java.sql.*;
import java.util.Scanner;

public class Dbutil {
    static String UserName;
    static String Password;
    static String url="jdbc:mysql://localhost:3306/Salary_Management_System";
    static String user_name="root";
    static String password="123456";
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;
    static String jdbc = "com.mysql.jdbc.Driver";


    public void setUserName(String userName) {
        UserName = userName;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public static Connection myConnection() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("请输入账户名：");
//            Scanner scanner=new Scanner(System.in);
//            UserName=scanner.next();
//            System.out.println("请输入密码：");
//            Password=scanner.next();
            //此处匹配用户名和密码，未完成
//            Connection connection;
//            connection=DriverManager.getConnection(url,UserName,Password);


            conn= DriverManager.getConnection(url,user_name,password);
            return conn;
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
