package sample.Insert_controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert_in_exwork {
    @FXML
    private ChoiceBox I_ex_id_box;

    @FXML
    private DatePicker I_ex_date;

    @FXML
    private ChoiceBox<?> I_ex_ewtype_box;

    @FXML
    private TextField I_ex_time;

    @FXML
    private Label I_ex_name;

    @FXML
    void Insert_ex_id_box_setItems() {
        ex_work_id_box_setItems.setItems(I_ex_id_box);
    }

    @FXML
    void Insert_ewtype_box_setItems() {
        ex_work_ewtype_box_setItems.setItems(I_ex_ewtype_box);
    }

    @FXML
    void Click_insert_exwork_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        String sql,name="";
        int ewtype=0;
        sql="select NAME from Employee_Basic_Information where id="+I_ex_id_box.getValue();
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
        I_ex_name.setText(name);
        sql="select ewtypeID from Extra_work_type where ewtypeNAME=\'"+I_ex_ewtype_box.getValue()+"\'";
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                ewtype=rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="insert into Extra_work_allowance(id, ewdate, ewtypeid, ewtime) values ("+I_ex_id_box.getValue()+",\'"+I_ex_date.getValue()+"\',"+ewtype+","+I_ex_time.getText()+")";
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
