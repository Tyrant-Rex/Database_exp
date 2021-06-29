package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Admin_controller.Admin_Controller;
import sample.Item.Employee;


import java.sql.*;

public class Show_e_basic_info {
    public static void show_table_e_basic(TableView<Employee> table, TableColumn id,TableColumn name,TableColumn sex,TableColumn age,TableColumn addr,TableColumn tel,TableColumn dept,TableColumn type)throws ClassNotFoundException{
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
            Admin_Controller.stmt=Admin_Controller.conn.createStatement();
            Admin_Controller.rs=Admin_Controller.stmt.executeQuery(sql_search_all);
            while(Admin_Controller.rs.next())
            {
                Info_list.add(new Employee(Admin_Controller.rs.getInt(1),Admin_Controller.rs.getString(2),Admin_Controller.rs.getString(3),Admin_Controller.rs.getInt(4),Admin_Controller.rs.getString(5),Admin_Controller.rs.getString(6),Admin_Controller.rs.getString(7),Admin_Controller.rs.getString(8)));
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
            if(Admin_Controller.ptmt!=null&&Admin_Controller.stmt!=null&&Admin_Controller.rs!=null)
            {
                try {
                    Admin_Controller.ptmt=null;
                    Admin_Controller.stmt=null;
                    Admin_Controller.rs=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
