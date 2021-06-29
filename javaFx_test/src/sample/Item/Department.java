package sample.Item;

public class Department {
    private int deptID;
    private String name;
    private int allowance;
    private String manager;
    private String telephone;

    public Department(int deptID, String name, int allowance, String manager, String telephone) {
        this.deptID = deptID;
        this.name = name;
        this.allowance = allowance;
        this.manager = manager;
        this.telephone = telephone;
    }

    public int getDeptID() {
        return deptID;
    }

    public String getName() {
        return name;
    }

    public int getAllowance() {
        return allowance;
    }

    public String getManager() {
        return manager;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
