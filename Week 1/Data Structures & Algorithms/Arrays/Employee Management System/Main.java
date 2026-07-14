public class Main {
    public static void main(String[] args) {
        EmployeeManagement manager =
                new EmployeeManagement(10);
        manager.addEmployee(
                new Employee(
                        101,
                        "Praveen",
                        "Manager",
                        75000));
        manager.addEmployee(
                new Employee(
                        102,
                        "Rahul",
                        "Developer",
                        60000));
        manager.addEmployee(
                new Employee(
                        103,
                        "Anjali",
                        "Analyst",
                        55000));
        System.out.println(
                "\n----- Employee Records -----");
        manager.displayEmployees();
        System.out.println(
                "\n----- Search Employee -----");
        Employee emp =
                manager.searchEmployee(102);
        if(emp != null) {
            System.out.println(emp);
        }
        System.out.println(
                "\n----- Delete Employee -----");
        manager.deleteEmployee(102);
        System.out.println(
                "\n----- Updated Employee Records -----");
        manager.displayEmployees();
    }
}
