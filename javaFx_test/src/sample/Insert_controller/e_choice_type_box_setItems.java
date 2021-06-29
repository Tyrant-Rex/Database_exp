package sample.Insert_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sample.sql_operation.Dbutil;

import java.sql.*;

public class e_choice_type_box_setItems {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;


    public static void setItems(ChoiceBox choiceBox)
    {
        String sql_search="select NAME from Employee_Type";
        choiceBox.getItems().clear();
        ObservableList<String> type_list= FXCollections.observableArrayList();
        try{
            conn= Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql_search);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                type_list.add(rs.getString(1));
                choiceBox.setItems(type_list);
            }
        } catch (SQLException e) {
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
