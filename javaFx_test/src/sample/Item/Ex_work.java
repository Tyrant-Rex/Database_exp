package sample.Item;

import java.sql.Date;

public class Ex_work {
    private int id;
    private String name;
    private Date date;
    private String type;
    private int time;
    private int allowance;

    public Ex_work(int id, String name, Date date, String type, int time, int allowance) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.time = time;
        this.allowance = allowance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }
}
