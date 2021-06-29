package sample.Insert_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert_in_dept {

    @FXML
    private TextField I_d_manager;

    @FXML
    private TextField I_d_name;

    @FXML
    private TextField I_d_allowance;

    @FXML
    private TextField I_d_tel;

    @FXML
    void Click_insert_d_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        String sql="insert into Department(NAME, ALLOWANCE, MANAGER, TELEPHONE) values (\'"+I_d_name.getText()+"\',"+I_d_allowance.getText()+",\'"+I_d_manager.getText()+"\',\'"+I_d_tel.getText()+"\')";
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
}
