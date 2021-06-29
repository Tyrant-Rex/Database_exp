package sample.Update_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Admin_controller.Admin_Controller;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_delete_dept_controller {
    @FXML
    private TextField U_D_allowance;

    @FXML
    private TextField U_D_name;

    @FXML
    private TextField U_D_manager;

    @FXML
    private TextField U_D_tel;

    @FXML
    void Click_update_dept_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        String name=U_D_name.getText();
        int allowance=Integer.parseInt(U_D_allowance.getText());
        String manager=U_D_manager.getText();
        String tel=U_D_tel.getText();
        String sql="update Department set name=\'"+name+"\',ALLOWANCE="+allowance+",MANAGER=\'"+manager+"\',TELEPHONE=\'"+tel+"\' where deptID="+ Admin_Controller.dept_deptID;
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
    void Click_delete_dept_info() {
        Connection connection=Dbutil.myConnection();
        PreparedStatement ptmt=null;
        String sql="delete from Department where deptID="+Admin_Controller.dept_deptID;
        try
        {
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
