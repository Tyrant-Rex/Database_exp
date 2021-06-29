package sample.Update_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Admin_controller.Admin_Controller;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_delete_exwork_controller {
    @FXML
    private DatePicker ex_work_date;

    @FXML
    private TextField U_D_ex_work_time;

    @FXML
    private ChoiceBox ex_work_type_choice_box;

    @FXML
    void Ex_work_choice_box_setItems() {
        Ex_work_choice_setItems.setItems(ex_work_type_choice_box);
    }

    @FXML
    void Click_Update_ex_work_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        int ewtypeID=0;
        String sql="select ewtypeID from Extra_work_type where ewtypeNAME = \'"+ex_work_type_choice_box.getValue()+"\'";
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                ewtypeID=rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="update Extra_work_allowance set ewdate=\'"+ex_work_date.getValue()+"\',ewtypeID="+ewtypeID+",ewtime="+U_D_ex_work_time.getText()+" where ID="+ Admin_Controller.e_basic_p_id;
        try {
            ptmt=connection.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
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
    void Click_delete_ex_work_info() {
        Connection connection=Dbutil.myConnection();
        PreparedStatement ptmt=null;
        String sql="delete from Extra_work_allowance where ID="+Admin_Controller.e_basic_p_id;
        try {
            ptmt=connection.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
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


}
