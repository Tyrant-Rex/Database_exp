package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.Department;
import sample.Item.Type;
import sample.Item.Year_info;

import java.sql.*;

public class Search_y_info {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;

    public static void Show_y_table_info(TableView<Year_info> table, TableColumn id,TableColumn name,TableColumn salary,TableColumn bonus,String select_name)
    {
        String sql="call calculate_Month_salary()";
        try {
            conn=Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql);
            ptmt.execute();
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
            ptmt=conn.prepareStatement(sql);
            ptmt.execute();
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
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql_search);
            while(rs.next())
            {
                Info_list.add(new Year_info(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
                table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
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
