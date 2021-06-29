package sample.Item;

public class month_salary {
    int id;
    String name;
    String dept;
    int salary;
    int dept_allowance;
    int ew_allowance;
    int deduction;
    int sum;


    public month_salary(int id, String name, String dept, int salary, int dept_allowance, int ew_allowance, int deduction, int sum) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.dept_allowance = dept_allowance;
        this.ew_allowance = ew_allowance;
        this.deduction = deduction;
        this.sum = sum;
    }

    public month_salary(String name, String dept, int salary, int dept_allowance, int ew_allowance, int deduction, int sum) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.dept_allowance = dept_allowance;
        this.ew_allowance = ew_allowance;
        this.deduction = deduction;
        this.sum = sum;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDept_allowance() {
        return dept_allowance;
    }

    public void setDept_allowance(int dept_allowance) {
        this.dept_allowance = dept_allowance;
    }

    public int getEw_allowance() {
        return ew_allowance;
    }

    public void setEw_allowance(int ew_allowance) {
        this.ew_allowance = ew_allowance;
    }

    public int getDeduction() {
        return deduction;
    }

    public void setDeduction(int deduction) {
        this.deduction = deduction;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
