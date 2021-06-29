package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.Employee;


import java.sql.*;

public class Show_e_basic_info {
//    static String url="jdbc:mysql://localhost:3306/Salary_Management_System";
//    static String user_name="root";
//    static String password="123456";
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;
//    static String jdbc = "com.mysql.jdbc.Driver";

//    public static void jdbcload() throws ClassNotFoundException {
//        Class.forName(jdbc);
//    }

    public static void show_table_e_basic(TableView<Employee> table, TableColumn id,TableColumn name,TableColumn sex,TableColumn age,TableColumn addr,TableColumn tel,TableColumn dept,TableColumn type)throws ClassNotFoundException{
//        jdbcload();
        String sql_search_all="select id, Employee_Basic_Information.name, sex, age, address, Employee_Basic_Information.telephone, Department.NAME, Employee_Type.NAME from Employee_Basic_Information,Department,Employee_Type where Department.deptID=Employee_Basic_Information.deptID and Employee_Type.typeID=Employee_Basic_Information.typeID";
        ObservableList<Employee> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        dept.setCellValueFactory(new PropertyValueFactory<>("department"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        try {
            conn= Dbutil.myConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql_search_all);
            while(rs.next())
            {
                Info_list.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(conn!=null&&ptmt!=null&&stmt!=null)
            {
                try {
                    conn.close();
                    ptmt.close();
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
