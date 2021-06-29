package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Admin_controller.Admin_Controller;
import sample.Item.Attend;
import sample.Item.Ex_work;
import sample.Item.Type;

import java.sql.*;

public class Search_ex_info {

    public static void Show_select_ex_info(TableView<Ex_work> table,TableColumn id ,TableColumn name, TableColumn date, TableColumn type, TableColumn time, TableColumn allowance, ChoiceBox choiceBox,String select_name)
    {
        String sql="call calculate_ewSalary()";
        try {
            Admin_Controller.ptmt=Admin_Controller.conn.prepareStatement(sql);
            Admin_Controller.ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        String month=(String) choiceBox.getValue();
        int month_select=0;
        String sql_search;
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
            if(month_select==0) sql_search="select Employee_Basic_Information.ID,NAME,ewdate,ewtypeNAME,ewtime,ewSALARY from Employee_Basic_Information,Extra_work_allowance,Extra_work_type where Employee_Basic_Information.ID=Extra_work_allowance.ID and Extra_work_allowance.ewtypeID=Extra_work_type.ewtypeID and NAME like \'%"+select_name+"%\'";
            else sql_search="select Employee_Basic_Information.ID,NAME,ewdate,ewtypeNAME,ewtime,ewSALARY from Employee_Basic_Information,Extra_work_allowance,Extra_work_type where Employee_Basic_Information.ID=Extra_work_allowance.ID and Extra_work_allowance.ewtypeID=Extra_work_type.ewtypeID and NAME like \'%"+select_name+"%\' and month(ewdate)="+month_select;
        }
        else sql_search="select Employee_Basic_Information.ID,NAME,ewdate,ewtypeNAME,ewtime,ewSALARY from Employee_Basic_Information,Extra_work_allowance,Extra_work_type where Employee_Basic_Information.ID=Extra_work_allowance.ID and Extra_work_allowance.ewtypeID=Extra_work_type.ewtypeID and NAME like \'%"+select_name+"%\'";
        ObservableList<Ex_work> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        allowance.setCellValueFactory(new PropertyValueFactory<>("allowance"));
        table.getItems().clear();
        try {
            Admin_Controller.ptmt=Admin_Controller.conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            Admin_Controller.rs=Admin_Controller.ptmt.executeQuery();
            while(Admin_Controller.rs.next())
            {
                Info_list.add(new Ex_work(Admin_Controller.rs.getInt(1),Admin_Controller.rs.getString(2),Admin_Controller.rs.getDate(3),Admin_Controller.rs.getString(4),Admin_Controller.rs.getInt(5),Admin_Controller.rs.getInt(6)));
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
