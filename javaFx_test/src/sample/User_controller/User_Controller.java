package sample.User_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.*;
import sample.sql_operation.Dbutil;

import java.sql.*;

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
            if(ptmt!=null&&stmt!=null&&rs!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                    rs=null;
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
            if(ptmt!=null&&stmt!=null&&rs!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                    rs=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    //-------------------------------------以上为员工界面考勤信息查询部分----------------------------------------------//
    @FXML
    TableView<Ex_work> U_ex_table;
    @FXML
    TableColumn U_ex_name;
    @FXML
    TableColumn U_ex_date;
    @FXML
    TableColumn U_ex_type;
    @FXML
    TableColumn U_ex_time;
    @FXML
    TableColumn U_ex_allowance;
    @FXML
    void Search_ex_info() {
        String sql_search="select NAME,ewdate,ewtypeNAME,ewtime,ewSALARY from Employee_Basic_Information,Extra_work_allowance,Extra_work_type where Employee_Basic_Information.ID=Extra_work_allowance.ID and Extra_work_allowance.ewtypeID=Extra_work_type.ewtypeID and Employee_Basic_Information.ID="+User_Login_Controller.p_id;
        ObservableList<Ex_work> Info_list= FXCollections.observableArrayList();
        U_ex_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        U_ex_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        U_ex_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        U_ex_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        U_ex_allowance.setCellValueFactory(new PropertyValueFactory<>("allowance"));
        U_ex_table.getItems().clear();
        try {
//            ptmt.setString(1,select_name);
            ptmt=conn.prepareStatement(sql_search);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                Info_list.add(new Ex_work(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                U_ex_table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(ptmt!=null&&stmt!=null&&rs!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                    rs=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //-------------------------------------以上为员工界面加班信息查询部分----------------------------------------------//

    @FXML
    TableView<month_salary> U_m_table;
    @FXML
    TableColumn U_m_name;
    @FXML
    TableColumn U_m_dept;
    @FXML
    TableColumn U_m_salary;
    @FXML
    TableColumn U_m_dept_allowance;
    @FXML
    TableColumn U_m_ew_allowance;
    @FXML
    TableColumn U_m_deduction;
    @FXML
    TableColumn U_m_sum;
    @FXML
    ChoiceBox U_m_choice_box;

    @FXML
    void Month_setItems() {
        U_m_choice_box.setItems(FXCollections.observableArrayList("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月",""));
        U_m_choice_box.setTooltip(new Tooltip("选择月份"));
    }

    @FXML
    void Search_m_info() {
        String month=(String)U_m_choice_box.getValue();
        int month_select=0;
        if(month==null) month_select=1;
        if(month!=null)
        {
            switch (month){
                case "一月":
                    month_select=1;
                    break;
                case "二月":
                    month_select=2;
                    break;
                case "三月":
                    month_select=3;
                    break;
                case "四月":
                    month_select=4;
                    break;
                case "五月":
                    month_select=5;
                    break;
                case "六月":
                    month_select=6;
                    break;
                case "七月":
                    month_select=7;
                    break;
                case "八月":
                    month_select=8;
                    break;
                case "九月":
                    month_select=9;
                    break;
                case "十月":
                    month_select=10;
                    break;
                case "十一月":
                    month_select=11;
                    break;
                case "十二月":
                    month_select=12;
                    break;
            }

        }
        String sql_search="select Employee_Basic_Information.ID,Employee_Basic_Information.NAME,Department.NAME,SALARY,ALLOWANCE,ewSALARY,sum(Deduction)" +
                "from Employee_Basic_Information left outer join Department on Employee_Basic_Information.deptID = Department.deptID left outer join Employee_Attend EA on Employee_Basic_Information.ID = EA.ID and month(notAttenddate)="+month_select+"  left outer join Extra_work_allowance Ewa on Employee_Basic_Information.ID = Ewa.ID and month(ewdate)="+month_select+"  left outer join Employee_Type on Employee_Basic_Information.typeID = Employee_Type.typeID" +
                " where Employee_Basic_Information.ID="+User_Login_Controller.p_id+
                " group by Employee_Basic_Information.ID,Employee_Basic_Information.NAME,SALARY,ALLOWANCE,ewSALARY";;
        int temp;
        ObservableList<month_salary> Info_list= FXCollections.observableArrayList();
        U_m_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        U_m_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        U_m_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        U_m_dept_allowance.setCellValueFactory(new PropertyValueFactory<>("dept_allowance"));
        U_m_ew_allowance.setCellValueFactory(new PropertyValueFactory<>("ew_allowance"));
        U_m_deduction.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        U_m_sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        U_m_table.getItems().clear();
        try {
            ptmt=conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                temp=0;
                temp=rs.getInt(4)+rs.getInt(5)+rs.getInt(6)-rs.getInt(7);
                Info_list.add(new month_salary(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),temp));
                U_m_table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(ptmt!=null&&stmt!=null&&rs!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                    rs=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //-------------------------------------以上为员工界面每月工资信息查询部分----------------------------------------------//
    @FXML
    TableView<Year_info> U_y_table;
    @FXML
    TableColumn U_y_name;
    @FXML
    TableColumn U_y_salary;
    @FXML
    TableColumn U_y_bonus;

    @FXML
    void Search_y_info() {
        String sql="call calculate_Month_salary()";
        try {
            ptmt=conn.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="call calculate_Year_bonus()";
        try {
            ptmt=conn.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        String sql_search="select NAME,year_salary,Bonus  from Sum_Month_Salary,Employee_Basic_Information where Employee_Basic_Information.ID=Sum_Month_Salary.ID and Employee_Basic_Information.ID="+User_Login_Controller.p_id;
        ObservableList<Year_info> Info_list= FXCollections.observableArrayList();
        U_y_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        U_y_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        U_y_bonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        U_y_table.getItems().clear();
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql_search);
            while(rs.next())
            {
                Info_list.add(new Year_info(rs.getString(1),rs.getInt(2),rs.getInt(3)));
                U_y_table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(ptmt!=null&&stmt!=null&&rs!=null)
            {
                try {
                    ptmt=null;
                    stmt=null;
                    rs=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //-------------------------------------以上为员工界面工资汇总信息查询部分----------------------------------------------//
}
