package sample.User_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.Attend;
import sample.Item.Employee;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_Controller {
    static Connection conn=Dbutil.myConnection();
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;

    @FXML
    TableView<Employee> U_e_table;
    @FXML
    TableColumn U_e_name;
    @FXML
    TableColumn U_e_sex;
    @FXML
    TableColumn U_e_age;
    @FXML
    TableColumn U_e_addr;
    @FXML
    TableColumn U_e_tel;
    @FXML
    TableColumn U_e_dept;
    @FXML
    TableColumn U_e_type;

    @FXML
    void Search_e_info() {
        String sql_search_e="select Employee_Basic_Information.name, sex, age, address, Employee_Basic_Information.telephone, Department.NAME, Employee_Type.NAME from Employee_Basic_Information,Employee_Type,Department where Employee_Type.typeID=Employee_Basic_Information.typeID and Department.deptID=Employee_Basic_Information.deptID and ID="+User_Login_Controller.p_id;
        ObservableList<Employee> Info_list= FXCollections.observableArrayList();
        U_e_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        U_e_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        U_e_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        U_e_addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        U_e_tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        U_e_dept.setCellValueFactory(new PropertyValueFactory<>("department"));
        U_e_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        U_e_table.getItems().clear();
        try {
            ptmt=conn.prepareStatement(sql_search_e);
//            ptmt.setString(1,select_name);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                Info_list.add(new Employee(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }

            U_e_table.setItems(Info_list);
        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(ptmt!=null&&stmt!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    //-------------------------------------以上为员工界面员工信息查询部分----------------------------------------------//

    @FXML
    TableView<Attend> U_a_table;
    @FXML
    TableColumn U_a_name;
    @FXML
    TableColumn U_a_date;
    @FXML
    TableColumn U_a_deduction;


    @FXML
    void Search_a_info(ActionEvent event) {
        String sql_search="select NAME,notAttenddate,Deduction from Employee_Attend,Employee_Basic_Information where Employee_Attend.ID=Employee_Basic_Information.ID and Employee_Basic_Information.ID="+User_Login_Controller.p_id;
        ObservableList<Attend> Info_list= FXCollections.observableArrayList();
        U_a_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        U_a_date.setCellValueFactory(new PropertyValueFactory<>("N_attend"));
        U_a_deduction.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        U_a_table.getItems().clear();
        try {
            ptmt=conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                Info_list.add(new Attend(rs.getString(1),rs.getDate(2),rs.getInt(3)));
                U_a_table.setItems(Info_list);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(ptmt!=null&&stmt!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    //-------------------------------------以上为员工界面考勤信息查询部分----------------------------------------------//




    //-------------------------------------以上为员工界面加班信息查询部分----------------------------------------------//

}
