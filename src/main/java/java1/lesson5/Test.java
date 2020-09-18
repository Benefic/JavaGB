package java1.lesson5;

public class Test {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван Иванович", "Директор", "dir@domain.com",
                                    "+79999999999", 120000f, 44);
        employees[1] = new Employee("Петров Петр Петрович", "Инженер", "ppp@domain.com",
                                    "+79999999998", 60000f, 38);
        employees[2] = new Employee("Лштшфум Ащьф Иванович", "Разведчик", "null@domain.com",
                                    "+79990000000", Float.MAX_VALUE, 42);
        employees[3] = new Employee("Сидоров Василий Николаевич", "Стажер", "st@domain.com",
                                    "+79999999997", 12000f, 22);
        employees[4] = new Employee("Кот Борис Тихонович", "Сторож", "guard@domain.com",
                                    "+79999999996", 60000f, 39);
        for (Employee employee : employees) {
            if (employee.getAge() > 40){
                employee.printInfo();
            }
        }

    }
}
