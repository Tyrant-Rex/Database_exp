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

public class Insert_in_e_basic {
    @FXML
    private TextField I_e_age;

    @FXML
    private TextField I_e_tel;

    @FXML
    private TextField I_e_name;

    @FXML
    private TextField I_e_addr;

    @FXML
    private TextField I_e_sex;

    @FXML
    private ChoiceBox I_e_type_box;

    @FXML
    private ChoiceBox I_e_dept_box;


    @FXML
    void Insert_e_dept_box_setItems() {
        e_choice_dept_box_setItems.setItems(I_e_dept_box);
    }

    @FXML
    void Insert_e_type_box_setItems() {
        e_choice_type_box_setItems.setItems(I_e_type_box);
    }

    @FXML
    void Click_insert_e_info() {
        Connection connection= Dbutil.myConnection();
        PreparedStatement ptmt=null;
        ResultSet rs=null;
        int dept_id=0;
        int type_id=0;
        String sql;
        sql="select deptID from Department where NAME=\'"+I_e_dept_box.getValue()+"\'";
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
        sql="select typeID from Employee_Type where name=\'"+I_e_type_box.getValue()+"\'";
        try{
            ptmt=connection.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next())
            {
                type_id=rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("操作失败");
            alert.showAndWait();
            return ;
        }
        sql="insert into Employee_Basic_Information(name, sex, age, address, telephone, typeid, deptid) values (\'"+I_e_name.getText()+"\',\'"+I_e_sex.getText()+"\',"+I_e_age.getText()+",\'"+I_e_addr.getText()+"\',\'"+I_e_tel.getText()+"\',"+type_id+","+dept_id+")";
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
