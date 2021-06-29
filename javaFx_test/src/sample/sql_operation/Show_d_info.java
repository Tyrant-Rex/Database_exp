package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import sample.Admin_controller.Admin_Controller;
import sample.Item.Department;


import javafx.scene.control.TableColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.cell.PropertyValueFactory;

public class Show_d_info {
    public static void show_table_d_info(TableView<Department> table, TableColumn id,TableColumn name,TableColumn allowance,TableColumn manager,TableColumn tel)
    {
        String sql_search_all="select * from Department";
        ObservableList<Department> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("deptID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        allowance.setCellValueFactory(new PropertyValueFactory<>("allowance"));
        manager.setCellValueFactory(new PropertyValueFactory<>("manager"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        try {
            Admin_Controller.stmt=Admin_Controller.conn.createStatement();
            Admin_Controller.rs=Admin_Controller.stmt.executeQuery(sql_search_all);
            while(Admin_Controller.rs.next())
            {
                Info_list.add(new Department(Admin_Controller.rs.getInt(1),Admin_Controller.rs.getString(2),Admin_Controller.rs.getInt(3),Admin_Controller.rs.getString(4),Admin_Controller.rs.getString(5)));
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
