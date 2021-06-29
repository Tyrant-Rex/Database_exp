package sample.Insert_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.sql_operation.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert_in_type {
    @FXML
    private ChoiceBox Insert_type_box;

    @FXML
    private TextField I_t_level;

    @FXML
    private TextField I_t_name;

    @FXML
    private TextField I_t_salary;

    @FXML
    void Insert_type_box_setItems() {
        type_box_setItems.setItems(Insert_type_box);
    }

    @FXML
    void Click_insert_type_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        int dept_id=0;
        String sql;
        sql="select deptID from Department where NAME=\'"+Insert_type_box.getValue()+"\'";
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                dept_id=rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="insert into Employee_Type(name, deptid, salary, level) VALUES (\'"+I_t_name.getText()+"\',"+dept_id+","+I_t_salary.getText()+","+I_t_level.getText()+")";
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
