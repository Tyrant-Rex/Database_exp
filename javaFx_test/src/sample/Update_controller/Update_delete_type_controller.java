package sample.Update_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.Admin_controller.Admin_Controller;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Update_delete_type_controller {
    @FXML
    private TextField U_D_type_name;
    @FXML
    private TextField U_D_type_salary;
    @FXML
    private TextField U_D_type_level;
    @FXML
    private ChoiceBox type_info_choice_box;


    @FXML
    void Click_Update_type_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        int dept_id=0;
        String sql;
        String dept=(String)type_info_choice_box.getValue();
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
        sql="update Employee_Type set NAME=\'"+U_D_type_name.getText()+"\',deptID="+dept_id+",SALARY="+U_D_type_salary.getText()+",LEVEL="+U_D_type_level.getText()+" where typeID="+ Admin_Controller.type_typeID;
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
    void Click_delete_type_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        String sql="delete from Employee_Type where typeID="+Admin_Controller.type_typeID;
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
    void type_info_setItems() {
        type_choice_box_setItems.setItems(type_info_choice_box);
    }
}
