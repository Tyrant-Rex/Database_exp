package sample.Item;

import java.sql.Date;

public class Attend {
    private int id;
    private String name;
    private Date N_attend;
    private int Deduction;

    public Attend(int id, String name, Date n_attend, int deduction) {
        this.id = id;
        this.name = name;
        N_attend = n_attend;
        Deduction = deduction;
    }

    public Attend(String name, Date n_attend, int deduction) {
        this.name = name;
        N_attend = n_attend;
        Deduction = deduction;
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

    public Date getN_attend() {
        return N_attend;
    }

    public void setN_attend(Date n_attend) {
        N_attend = n_attend;
    }

    public int getDeduction() {
        return Deduction;
    }

    public void setDeduction(int deduction) {
        Deduction = deduction;
    }
}
