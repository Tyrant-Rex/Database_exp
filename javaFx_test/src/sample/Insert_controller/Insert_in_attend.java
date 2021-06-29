package sample.Insert_controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert_in_attend {

    @FXML
    private ChoiceBox I_attend_box;

    @FXML
    private DatePicker I_attend_date;

    @FXML
    private TextField I_attend_deduction;

    @FXML
    private Label I_attend_name;

    @FXML
    void Insert_attend_box_setItems() {
        attend_box_setItems.setItems(I_attend_box);
    }

    @FXML
    void Click_insert_attend_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        String sql,name="";
        sql="select NAME from Employee_Basic_Information where id="+I_attend_box.getValue();
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                name=rs.getString(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        I_attend_name.setText(name);
        sql="insert into Employee_Attend(notAttenddate, ID, Deduction) VALUES (\'"+I_attend_date.getValue()+"\',"+I_attend_box.getValue()+","+I_attend_deduction.getText()+")";
        try
        {
            ptmt=connection.prepareStatement(sql);
            ptmt.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }finally {
            if(connection!=null&&ptmt!=null)
            {
                try {
                    connection.close();
                    ptmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
