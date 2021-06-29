package sample.Update_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_work_choice_setItems {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    public static void setItems(ChoiceBox choiceBox)
    {
        String sql="select ewtypeNAME from Extra_work_type";
        choiceBox.getItems().clear();
        ObservableList<String> ex_list= FXCollections.observableArrayList();
        try{
            conn= Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                ex_list.add(rs.getString(1));
                choiceBox.setItems(ex_list);
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
