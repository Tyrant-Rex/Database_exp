package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Admin_controller.Admin_Controller;
import sample.Item.Department;
import sample.Item.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Show_t_info {
    public static void show_table_t_info(TableView<Type> table, TableColumn id,TableColumn name,TableColumn dept,TableColumn salary,TableColumn level)
    {
        String sql_search_all="select Employee_Type.typeID,Employee_Type.NAME,Department.NAME,Employee_Type.SALARY,Employee_Type.LEVEL from Employee_Type,Department where Employee_Type.deptID=Department.deptID";
        ObservableList<Type> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        try {
            Admin_Controller.stmt=Admin_Controller.conn.createStatement();
            Admin_Controller.rs=Admin_Controller.stmt.executeQuery(sql_search_all);
            while(Admin_Controller.rs.next())
            {
                Info_list.add(new Type(Admin_Controller.rs.getInt(1),Admin_Controller.rs.getString(2),Admin_Controller.rs.getString(3),Admin_Controller.rs.getInt(4),Admin_Controller.rs.getInt(5)));
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
