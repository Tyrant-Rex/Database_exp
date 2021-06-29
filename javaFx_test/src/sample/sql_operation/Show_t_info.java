package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.Department;
import sample.Item.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Show_t_info {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;

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
            conn= Dbutil.myConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql_search_all);
            while(rs.next())
            {
                Info_list.add(new Type(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
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
