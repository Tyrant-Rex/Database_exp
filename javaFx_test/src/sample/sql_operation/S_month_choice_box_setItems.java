package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sample.Admin_controller.Admin_Controller;
import sample.Item.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class S_month_choice_box_setItems {


    public static void s_month_dept_choice_box_setItems(ChoiceBox choiceBox)
    {
        String sql_search="select name from Department";
        choiceBox.getItems().clear();
        ObservableList<String> Info_list= FXCollections.observableArrayList();
        try {

            Admin_Controller.ptmt=Admin_Controller.conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            Admin_Controller.rs=Admin_Controller.ptmt.executeQuery();
            while(Admin_Controller.rs.next())
            {
                Info_list.add(Admin_Controller.rs.getString(1));
                choiceBox.setItems(Info_list);
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
