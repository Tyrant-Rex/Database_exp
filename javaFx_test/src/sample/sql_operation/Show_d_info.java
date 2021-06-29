package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import sample.Item.Department;


import javafx.scene.control.TableColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.cell.PropertyValueFactory;

public class Show_d_info {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;
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
            conn= Dbutil.myConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql_search_all);
            while(rs.next())
            {
                Info_list.add(new Department(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5)));
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
