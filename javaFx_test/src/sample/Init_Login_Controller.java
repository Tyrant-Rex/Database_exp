package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class Init_Login_Controller {
    @FXML
    private Button login_user_button;
    @FXML
    private Button login_admin_button;


    @FXML
    void Choose_user_login(ActionEvent event) throws IOException {
        Stage currentStage=(Stage)login_user_button.getScene().getWindow();
        currentStage.close();
        Parent root= FXMLLoader.load(getClass().getResource("Login_fxml/user_login_ui.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("用户登录界面");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void Choose_admin_login(ActionEvent event) throws IOException {
        Stage currentStage=(Stage)login_user_button.getScene().getWindow();
        currentStage.close();
        Parent root= FXMLLoader.load(getClass().getResource("Login_fxml/admin_login_ui.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("管理员登录界面");
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}
