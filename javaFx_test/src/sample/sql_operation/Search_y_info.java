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
import sample.Item.Year_info;

import java.sql.*;

public class Search_y_info {


    public static void Show_y_table_info(TableView<Year_info> table, TableColumn id,TableColumn name,TableColumn salary,TableColumn bonus,String select_name)
    {
        String sql="call calculate_Month_salary()";
        try {
            Admin_Controller.ptmt=Admin_Controller.conn.prepareStatement(sql);
            Admin_Controller.ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="call calculate_Year_bonus()";
        try {
            Admin_Controller.ptmt=Admin_Controller.conn.prepareStatement(sql);
            Admin_Controller.ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        String sql_search="select Sum_Month_Salary.ID,NAME,year_salary,Bonus  from Sum_Month_Salary,Employee_Basic_Information where Employee_Basic_Information.ID=Sum_Month_Salary.ID and name like \'%"+select_name+"%\'";
        ObservableList<Year_info> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        bonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        try {
            Admin_Controller.stmt=Admin_Controller.conn.createStatement();
            Admin_Controller.rs=Admin_Controller.stmt.executeQuery(sql_search);
            while(Admin_Controller.rs.next())
            {
                Info_list.add(new Year_info(Admin_Controller.rs.getInt(1),Admin_Controller.rs.getString(2),Admin_Controller.rs.getInt(3),Admin_Controller.rs.getInt(4)));
                table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
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
