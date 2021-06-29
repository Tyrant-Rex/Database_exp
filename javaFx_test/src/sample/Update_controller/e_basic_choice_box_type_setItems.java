package sample.Update_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sample.sql_operation.Dbutil;

import java.sql.*;

public class e_basic_choice_box_type_setItems {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;

    public static void setItems(ChoiceBox type)
    {
        String sql_search="select NAME from Employee_Type";
        type.getItems().clear();
        ObservableList<String> type_list= FXCollections.observableArrayList();
        try{
            conn=Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql_search);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                type_list.add(rs.getString(1));
                type.setItems(type_list);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        finally {
            if(conn!=null&&ptmt!=null)
            {
                try {
                    conn.close();
                    ptmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }




}
