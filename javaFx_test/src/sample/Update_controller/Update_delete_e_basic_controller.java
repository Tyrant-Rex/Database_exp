package sample.Update_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sample.Admin_controller.Admin_Controller;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_delete_e_basic_controller {

    @FXML
    private ChoiceBox e_basic_info_Choice_box_type;
    @FXML
    private ChoiceBox e_basic_info_Choice_box_dept;
    @FXML
    void Click_update_e_basic_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        int dept_id=0;
        int type_id=0;
        String sql;
        String dept=(String)e_basic_info_Choice_box_dept.getValue();
        sql="select deptID from Department where NAME=\'"+dept+"\'";
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                dept_id=rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        String type=(String)e_basic_info_Choice_box_type.getValue();
        sql="select typeID from Employee_Type where name=\'"+type+"\'";
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                type_id=rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="update Employee_Basic_Information set deptID="+dept_id+",typeID="+type_id+" where ID="+ Admin_Controller.e_basic_p_id;
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
    void Click_delete_e_basic_info() {
        Connection connection=Dbutil.myConnection();
        PreparedStatement ptmt=null;
        String sql="delete from Employee_Basic_Information where ID="+Admin_Controller.e_basic_p_id;
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

    @FXML
    void e_basic_dept_setItems() {
        e_basic_choice_box_dept_setItems.setItems(e_basic_info_Choice_box_dept);
    }

    @FXML
    void e_basic_type_setItems() {
        e_basic_choice_box_type_setItems.setItems(e_basic_info_Choice_box_type);
    }
}
