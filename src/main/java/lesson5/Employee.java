package lesson5;

public class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;
    private float salary;
    private int age;

    public Employee(String name, String position, String email, String phone, float salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public float getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.println("Employee: " + name + ", " + position + ", " + email + ", " + phone + ", " + salary + ", " + age);
    }
}
