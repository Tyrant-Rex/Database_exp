package sample.Admin_controller;

import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Item.*;
import sample.sql_operation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Admin_Controller {
    public Admin_Controller() {
    }
    public static Connection conn=Dbutil.myConnection();
    public static PreparedStatement ptmt=null;
    public static ResultSet rs=null;
    public static Statement stmt=null;
    @FXML
    private TableView<Employee> Basic_information_table;
    @FXML
    private TableColumn B_c_id;
    @FXML
    private TableColumn B_c_name;
    @FXML
    private TableColumn B_c_sex;
    @FXML
    private TableColumn B_c_age;
    @FXML
    private TableColumn B_c_addr;
    @FXML
    private TableColumn B_c_tel;
    @FXML
    private TableColumn B_c_type;
    @FXML
    private TableColumn B_c_dept;
    @FXML
    private TextField input_e_name;

    @FXML
    void Click_search_e_all(ActionEvent event) throws ClassNotFoundException {
        Show_e_basic_info.show_table_e_basic(Basic_information_table,B_c_id,B_c_name,B_c_sex,B_c_age,B_c_addr,B_c_tel,B_c_dept,B_c_type);
    }

    @FXML
    void Click_search_e_one(ActionEvent event) {
        Search_e_info.Show_select_e_info(Basic_information_table,B_c_id,B_c_name,B_c_sex,B_c_age,B_c_addr,B_c_tel,B_c_dept,B_c_type,input_e_name.getText());
    }

    //----------------------------------以上为员工相关------------------------------//

    @FXML
    private TableView<Department> Dept_info_table;
    @FXML
    private TableColumn D_c_id;
    @FXML
    private TableColumn D_c_name;
    @FXML
    private TableColumn D_c_allowance;
    @FXML
    private TableColumn D_c_manager;
    @FXML
    private TableColumn D_c_tel;
    @FXML
    private TextField input_d_name;

    @FXML
    void Click_search_d_one(ActionEvent event) {
        Search_d_info.Show_select_d_info(Dept_info_table,D_c_id,D_c_name,D_c_allowance,D_c_manager,D_c_tel,input_d_name.getText());
    }

    @FXML
    void Click_search_d_all(ActionEvent event) {
        Show_d_info.show_table_d_info(Dept_info_table,D_c_id,D_c_name,D_c_allowance,D_c_manager,D_c_tel);
    }

    //----------------------------------以上为部门相关------------------------------//
    @FXML
    private TableView<Type> Type_info_table;
    @FXML
    private TableColumn T_c_id;
    @FXML
    private TableColumn T_c_name;
    @FXML
    private TableColumn T_c_dept;
    @FXML
    private TableColumn T_c_salary;
    @FXML
    private TableColumn T_c_level;
    @FXML
    private TextField input_t_name;

    @FXML
    void Click_search_t_one(ActionEvent event) {
        Search_t_info.Show_select_t_info(Type_info_table,T_c_id,T_c_name,T_c_dept,T_c_salary,T_c_level,input_t_name.getText());
    }

    @FXML
    void Click_search_t_all(ActionEvent event) {
        Show_t_info.show_table_t_info(Type_info_table,T_c_id,T_c_name,T_c_dept,T_c_salary,T_c_level);
    }


    //----------------------------------以上为职位相关------------------------------//
    @FXML
    private TableView<Attend> Attend_info_table;
    @FXML
    private TableColumn A_c_id;
    @FXML
    private TableColumn A_c_name;
    @FXML
    private TableColumn A_c_date;
    @FXML
    private TableColumn A_c_Duc;
    @FXML
    private TextField input_a_name;
    @FXML
    private ChoiceBox a_choice_box;

    public void Choice_box_a_setItems()
    {
        a_choice_box.setItems(FXCollections.observableArrayList("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月",""));
        a_choice_box.setTooltip(new Tooltip("选择月份"));
    }

    @FXML
    void Click_search_a_one(ActionEvent event) {
        Search_a_info.Show_select_a_info(Attend_info_table,A_c_id,A_c_name,A_c_date,A_c_Duc,a_choice_box,input_a_name.getText());
    }

    //----------------------------------以上为考勤相关------------------------------//
    @FXML
    private TableView<Ex_work> exwork_info_table;
    @FXML
    private TableColumn Ex_c_id;
    @FXML
    private TableColumn EX_c_name;
    @FXML
    private TableColumn EX_c_date;
    @FXML
    private TableColumn EX_c_type;
    @FXML
    private TableColumn EX_c_time;
    @FXML
    private TableColumn EX_c_allowance;
    @FXML
    private TextField input_ex_name;
    @FXML
    private ChoiceBox ex_choice_box;

    @FXML
    void Choice_box_ex_setItems() {
        ex_choice_box.setItems(FXCollections.observableArrayList("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月",""));
        ex_choice_box.setTooltip(new Tooltip("选择月份"));
    }

    @FXML
    void Click_search_ex_one(ActionEvent event) {
        Search_ex_info.Show_select_ex_info(exwork_info_table,Ex_c_id,EX_c_name,EX_c_date,EX_c_type,EX_c_time,EX_c_allowance,ex_choice_box,input_ex_name.getText());
    }


    //----------------------------------以上为加班相关------------------------------//
    @FXML
    private TableView<month_salary> s_month_info_table;
    @FXML
    private TableColumn M_c_id;
    @FXML
    private TableColumn M_c_dept;
    @FXML
    private TableColumn M_c_name;
    @FXML
    private TableColumn M_c_salary;
    @FXML
    private TableColumn M_c_dept_allowance;
    @FXML
    private TableColumn M_c_ew_allowance;
    @FXML
    private TableColumn M_c_deduction;
    @FXML
    private TableColumn M_c_sum;
    @FXML
    private TextField input_s_month_name;
    @FXML
    private ChoiceBox s_month_choice_box;
    @FXML
    private ChoiceBox s_month_dept_choice_box;

    @FXML
    void Choice_box_s_month_setItems() {
        s_month_choice_box.setItems(FXCollections.observableArrayList("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月",""));
        s_month_choice_box.setTooltip(new Tooltip("选择月份"));
    }

    @FXML
    void Choice_box_s_month_dept_setItems() {
        S_month_choice_box_setItems.s_month_dept_choice_box_setItems(s_month_dept_choice_box);
        s_month_dept_choice_box.setTooltip(new Tooltip("选择部门"));
    }

    @FXML
    void Click_search_s_month_one(ActionEvent event) {
        Search_s_month_info.Show_select_s_month_info(s_month_info_table,M_c_id,M_c_name,M_c_dept,M_c_salary,M_c_dept_allowance,M_c_ew_allowance,M_c_deduction,M_c_sum,s_month_choice_box,s_month_dept_choice_box,input_s_month_name.getText());
    }




    //----------------------------------以上为员工月工资相关------------------------------//
    @FXML
    TableView<Year_info> y_info_table;
    @FXML
    TableColumn Y_c_id;
    @FXML
    TableColumn Y_c_name;
    @FXML
    TableColumn Y_c_salary;
    @FXML
    TableColumn Y_c_bonus;
    @FXML
    TextField input_y_name;

    @FXML
    void Click_search_y(ActionEvent event) {
        Search_y_info.Show_y_table_info(y_info_table,Y_c_id,Y_c_name,Y_c_salary,Y_c_bonus,input_y_name.getText());
    }

    //----------------------------------以上为员工年终汇总相关------------------------------//
    public static int e_basic_p_id;
    @FXML
    private ChoiceBox e_basic_info_Choice_box_dept;
    public void Update_delete_e_basic_info() throws IOException {
            TableView<Employee> tv=Basic_information_table;
            Employee e=tv.getSelectionModel().getSelectedItem();
            e_basic_p_id=e.getId();
            AnchorPane root= FXMLLoader.load(getClass().getResource("../Update_fxml/Update_delete_e_basic_info.fxml"));
            Stage newStage=new Stage();
            newStage.setTitle("修改");
            newStage.setScene(new Scene(root));
            newStage.show();
    }

    public static int dept_deptID;
    public void Update_delete_dept_info()throws IOException
    {
        TableView<Department> tv=Dept_info_table;
        Department d=tv.getSelectionModel().getSelectedItem();
        dept_deptID=d.getDeptID();
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Update_fxml/Update_delete_dept_info.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("修改");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    public static int type_typeID;
    @FXML
    void Update_delete_type_info() throws IOException {
        TableView<Type> tv=Type_info_table;
        Type t=tv.getSelectionModel().getSelectedItem();
        type_typeID=t.getId();
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Update_fxml/Update_delete_type_info.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("修改");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void Update_delete_attend_info() throws IOException {
        TableView<Attend> tv=Attend_info_table;
        Attend a=tv.getSelectionModel().getSelectedItem();
        e_basic_p_id=a.getId();
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Update_fxml/Update_delete_attend_info.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("修改");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void Update_delete_exwork_info() throws IOException {
        TableView<Ex_work> tv=exwork_info_table;
        Ex_work e=tv.getSelectionModel().getSelectedItem();
        e_basic_p_id=e.getId();
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Update_fxml/Update_delete_exwork_info.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("修改");
        newStage.setScene(new Scene(root));
        newStage.show();
    }
    //----------------------------------以上为修改表相关------------------------------//
    @FXML
    void Insert_e_basic() throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Insert_fxml/e_basic_insert.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("添加");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void Insert_dept() throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Insert_fxml/dept_insert.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("添加");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void Insert_type() throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Insert_fxml/type_insert.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("添加");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void Insert_attend() throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Insert_fxml/attend_insert.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("添加");
        newStage.setScene(new Scene(root));
        newStage.show();
    }


    @FXML
    void Click_insert_exwork_info() throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("../Insert_fxml/exwork_insert.fxml"));
        Stage newStage=new Stage();
        newStage.setTitle("添加");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    //----------------------------------以上为添加表相关------------------------------//
}
