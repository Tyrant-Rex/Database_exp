package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root=FXMLLoader.load(getClass().getResource("Login_fxml/login.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("登录界面");
        newStage.setScene(new Scene(root,600,400));
        newStage.show();


//        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
//        primaryStage.setTitle("工资管理系统");
//        primaryStage.setScene(new Scene(root, 1012, 800));
//        primaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
//        primaryStage.setTitle("工资管理系统");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

