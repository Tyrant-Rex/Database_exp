package sample.Update_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Admin_controller.Admin_Controller;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_delete_attend_controller {
    @FXML
    private TextField U_D_not_attend_deduction;

    @FXML
    private DatePicker U_D_not_attend_date;

    @FXML
    void Click_Update_attend_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        String sql="update Employee_Attend set notAttenddate=\'"+U_D_not_attend_date.getValue()+"\',Deduction="+U_D_not_attend_deduction.getText()+" where ID="+ Admin_Controller.e_basic_p_id;
        try
        {
            ptmt=connection.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(connection!=null&&ptmt!=null)
            {
                try {
                    connection.close();
                    ptmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    @FXML
    void Click_Delete_attend_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        String sql="delete from Employee_Attend where ID="+Admin_Controller.e_basic_p_id;
        try
        {
            ptmt=connection.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            if(connection!=null&&ptmt!=null)
            {
                try {
                    connection.close();
                    ptmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
