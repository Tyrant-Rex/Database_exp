package sample.Item;

public class Employee {
    private int id;
    private String name;
    private String sex;
    private int age;
    private String address;
    private String telephone;
    private String department;
    private String type;

    public Employee(){}

    public Employee(int id, String name, String sex, int age, String address, String telephone, String department, String type) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
        this.department = department;
        this.type = type;
    }

    public Employee(String name, String sex, int age, String address, String telephone, String department, String type) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
        this.department = department;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getDepartment() {
        return department;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setType(String type) {
        this.type = type;
    }
}
