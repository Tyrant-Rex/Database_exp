package sample.Admin_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.sql_operation.Dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_Login_Controller {
    @FXML
    private TextField Admin_name;
    @FXML
    private PasswordField Admin_password;
    @FXML
    private Button Admin_login_button;

    @FXML
    void Click_Admin_login() {
        Connection connection= Dbutil.myConnection();
        if(connection==null){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("连接数据库失败！");
            alert.show();
        }
        else{
            if(Admin_name.getText().equals(""))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("账号不可为空");
                alert.showAndWait();
                return ;
            }else if(Admin_password.getText().equals(""))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("密码不可为空");
                alert.showAndWait();
                return ;
            }
        }
        try{
            String sql="select * from Admin_login_info_table where id=?";
            PreparedStatement ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,Admin_name.getText());
            ResultSet rs=ptmt.executeQuery();
            if(!(rs.next()))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("该用户名不存在");
                alert.showAndWait();
                return ;
            }
            String realPassword=rs.getString("Password");
            String inputPassword=Admin_password.getText();
            if(!(realPassword.equals(inputPassword)))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("密码错误，请重新输入");
                alert.showAndWait();
                return ;
            }
            else {
                Stage currentStage=(Stage)Admin_login_button.getScene().getWindow();
                currentStage.close();
                Parent root= FXMLLoader.load(getClass().getResource("../Admin.fxml"));
                Stage newStage=new Stage();
                newStage.setTitle("工资管理系统 当前用户："+Admin_name.getText());
                newStage.setScene(new Scene(root));
                newStage.show();
                ptmt.close();
                connection.close();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
