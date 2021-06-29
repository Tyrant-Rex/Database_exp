package sample.Item;

public class Type {
    private int id;
    private String name;
    private String dept;
    private int salary;
    private int level;

    public Type(int id, String name, String dept, int salary, int level) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
