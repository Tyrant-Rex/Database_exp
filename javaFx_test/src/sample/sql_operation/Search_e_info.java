package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Search_e_info {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;

    public static void Show_select_e_info(TableView<Employee> table, TableColumn id, TableColumn name, TableColumn sex, TableColumn age, TableColumn addr, TableColumn tel, TableColumn dept, TableColumn type, String select_name)
    {
        String sql_search_e="select id, Employee_Basic_Information.name, sex, age, address, Employee_Basic_Information.telephone, Department.NAME, Employee_Type.NAME from Employee_Basic_Information,Employee_Type,Department where Employee_Type.typeID=Employee_Basic_Information.typeID and Department.deptID=Employee_Basic_Information.deptID and Employee_Basic_Information.NAME like \'%" + select_name + "%\'";
        ObservableList<Employee> Info_list=FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        dept.setCellValueFactory(new PropertyValueFactory<>("department"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        table.getItems().clear();
        try {
            conn= Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql_search_e);
//            ptmt.setString(1,select_name);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                Info_list.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }

            table.setItems(Info_list);
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
