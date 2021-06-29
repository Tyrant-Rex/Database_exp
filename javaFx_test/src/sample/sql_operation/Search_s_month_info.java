package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Admin_controller.Admin_Controller;
import sample.Item.Ex_work;
import sample.Item.month_salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search_s_month_info {


    public static void Show_select_s_month_info(TableView<month_salary> table,TableColumn id, TableColumn name,TableColumn dept, TableColumn salary, TableColumn dept_allowance, TableColumn ew_allowance, TableColumn deduction, TableColumn sum, ChoiceBox choiceBox_month,ChoiceBox choiceBox_dept,String select_name)
    {
        String month=(String) choiceBox_month.getValue();
        int month_select=0;
        String dept_select=(String) choiceBox_dept.getValue();
        int temp;
//        String sql_search = "select Employee_Basic_Information.ID,Employee_Basic_Information.NAME,Department.NAME,SALARY,ALLOWANCE,ewSALARY,sum(Deduction)" +
//                "from Employee_Basic_Information left outer join Department on Employee_Basic_Information.deptID = Department.deptID left outer join Employee_Attend EA on Employee_Basic_Information.ID = EA.ID and month(notAttenddate)="+1+"  left outer join Extra_work_allowance Ewa on Employee_Basic_Information.ID = Ewa.ID and month(ewdate)="+month_select+"  left outer join Employee_Type on Employee_Basic_Information.typeID = Employee_Type.typeID" +
//                " where Employee_Basic_Information.NAME like \'%"+select_name+"%\'" +
//                " group by Employee_Basic_Information.ID,Employee_Basic_Information.NAME,SALARY,ALLOWANCE,ewSALARY";
        if(dept_select==null) dept_select="";
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
                " where Employee_Basic_Information.NAME like \'%"+select_name+"%\'" +" and Department.NAME like \'%"+dept_select+"%\'"+
                " group by Employee_Basic_Information.ID,Employee_Basic_Information.NAME,SALARY,ALLOWANCE,ewSALARY";
//        else if(month!=null)
//        {
//            switch (month){
//                case "一月":
//                    month_select=1;
//                    break;
//                case "二月":
//                    month_select=2;
//                    break;
//                case "三月":
//                    month_select=3;
//                    break;
//                case "四月":
//                    month_select=4;
//                    break;
//                case "五月":
//                    month_select=5;
//                    break;
//                case "六月":
//                    month_select=6;
//                    break;
//                case "七月":
//                    month_select=7;
//                    break;
//                case "八月":
//                    month_select=8;
//                    break;
//                case "九月":
//                    month_select=9;
//                    break;
//                case "十月":
//                    month_select=10;
//                    break;
//                case "十一月":
//                    month_select=11;
//                    break;
//                case "十二月":
//                    month_select=12;
//                    break;
//            }
//            if(month_select==0) System.out.println("error");//之后做弹框处理 叫做月份选择
//            else sql_search="select Employee_Basic_Information.ID,Employee_Basic_Information.NAME,Department.NAME,SALARY,ALLOWANCE,ewSALARY,sum(Deduction)" +
//                    "from Employee_Basic_Information left outer join Department on Employee_Basic_Information.deptID = Department.deptID left outer join Employee_Attend EA on Employee_Basic_Information.ID = EA.ID and month(notAttenddate)="+month_select+"  left outer join Extra_work_allowance Ewa on Employee_Basic_Information.ID = Ewa.ID and month(ewdate)="+month_select+"  left outer join Employee_Type on Employee_Basic_Information.typeID = Employee_Type.typeID" +
//                    " where Employee_Basic_Information.NAME like \'%"+select_name+"%\'" +" and Department.NAME like \'"+dept_select+"\'"+
//                    " group by Employee_Basic_Information.ID,Employee_Basic_Information.NAME,SALARY,ALLOWANCE,ewSALARY";
//        }
//        else System.out.println("error");//之后做弹框处理 叫做月份选择
        ObservableList<month_salary> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        dept_allowance.setCellValueFactory(new PropertyValueFactory<>("dept_allowance"));
        ew_allowance.setCellValueFactory(new PropertyValueFactory<>("ew_allowance"));
        deduction.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        table.getItems().clear();
        try {
            Admin_Controller.ptmt=Admin_Controller.conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            Admin_Controller.rs=Admin_Controller.ptmt.executeQuery();
            while(Admin_Controller.rs.next())
            {
                temp=0;
                temp=Admin_Controller.rs.getInt(4)+Admin_Controller.rs.getInt(5)+Admin_Controller.rs.getInt(6)-Admin_Controller.rs.getInt(7);
                Info_list.add(new month_salary(Admin_Controller.rs.getInt(1),Admin_Controller.rs.getString(2),Admin_Controller.rs.getString(3),Admin_Controller.rs.getInt(4),Admin_Controller.rs.getInt(5),Admin_Controller.rs.getInt(6),Admin_Controller.rs.getInt(7),temp));
                table.setItems(Info_list);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(Admin_Controller.ptmt!=null&&Admin_Controller.stmt!=null&&Admin_Controller.rs!=null)
            {
                try {
                    Admin_Controller.ptmt=null;
                    Admin_Controller.stmt=null;
                    Admin_Controller.rs=null;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
