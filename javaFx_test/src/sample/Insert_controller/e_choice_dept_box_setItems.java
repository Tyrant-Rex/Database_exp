package sample.Insert_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class e_choice_dept_box_setItems {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;

    public static void setItems(ChoiceBox choiceBox)
    {
        String sql_search="select name from Department";
        choiceBox.getItems().clear();
        ObservableList<String> dept_list= FXCollections.observableArrayList();
        try {
            conn= Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                dept_list.add(rs.getString(1));
                choiceBox.setItems(dept_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        finally {
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
