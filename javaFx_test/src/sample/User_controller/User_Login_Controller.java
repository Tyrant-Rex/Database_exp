package sample.User_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import sample.sql_operation.Dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User_Login_Controller {

    public User_Login_Controller(){}
    public static int p_id;
    @FXML
    private TextField User_name;
    @FXML
    private PasswordField User_password;
    @FXML
    private Button User_login_button;
    @FXML
    public void Click_User_login(ActionEvent event) {
        Connection connection= Dbutil.myConnection();
        if(connection==null){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("连接数据库失败！");
            alert.show();
        }
        else{
            if(User_name.getText().equals(""))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("账号不可为空");
                alert.showAndWait();
                return ;
            }else if(User_password.getText().equals(""))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("密码不可为空");
                alert.showAndWait();
                return ;
            }
        }
        try{
            String sql="select * from User_login_info_table where id=?";
            PreparedStatement ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,User_name.getText());
            ResultSet rs=ptmt.executeQuery();
            if(!(rs.next()))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("该用户名不存在");
                alert.showAndWait();
                return ;
            }
            String realPassword=rs.getString("Password");
            String inputPassword=User_password.getText();
            p_id=rs.getInt(3);
            if(!(realPassword.equals(inputPassword)))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("密码错误，请重新输入");
                alert.showAndWait();
                return ;
            }
            else {
                Stage currentStage=(Stage)User_login_button.getScene().getWindow();
                currentStage.close();
                Parent root= FXMLLoader.load(getClass().getResource("../User.fxml"));
                Stage newStage=new Stage();
                newStage.setTitle("工资管理系统 当前用户："+User_name.getText()+" 员工ID："+p_id);
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
