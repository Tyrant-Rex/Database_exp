package sample.sql_operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Item.Attend;
import sample.Item.Department;
import sample.Item.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search_a_info {
    static Connection conn=null;
    static PreparedStatement ptmt=null;
    static ResultSet rs=null;
    static Statement stmt=null;

    public static void Show_select_a_info(TableView<Attend> table,TableColumn id, TableColumn name, TableColumn date, TableColumn deduction, ChoiceBox choiceBox, String select_name)
    {
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
            if(month_select==0) sql_search="select Employee_Basic_Information.ID,NAME,notAttenddate,Deduction from Employee_Attend,Employee_Basic_Information where Employee_Attend.ID=Employee_Basic_Information.ID and NAME like \'%"+select_name+"%\'";
            else sql_search="select Employee_Basic_Information.ID, NAME,notAttenddate,Deduction from Employee_Attend,Employee_Basic_Information where Employee_Attend.ID=Employee_Basic_Information.ID and NAME like \'%"+select_name+"%\' and month(notAttenddate)="+month_select;
        }
        else sql_search="select Employee_Basic_Information.ID,NAME,notAttenddate,Deduction from Employee_Attend,Employee_Basic_Information where Employee_Attend.ID=Employee_Basic_Information.ID and NAME like \'%"+select_name+"%\'";
        ObservableList<Attend> Info_list= FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("N_attend"));
        deduction.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        table.getItems().clear();
        try {
            conn= Dbutil.myConnection();
            ptmt=conn.prepareStatement(sql_search);
//            ptmt.setString(1,select_name);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                Info_list.add(new Attend(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4)));
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
            if(conn!=null&&ptmt!=null&&stmt!=null)
            {
                try {
                    conn.close();
                    ptmt.close();
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }



    }
}
