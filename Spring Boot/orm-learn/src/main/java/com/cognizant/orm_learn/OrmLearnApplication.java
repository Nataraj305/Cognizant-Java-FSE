package com.cognizant.orm_learn;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.service.CountryService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.StockService;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    @Autowired
    private CountryService countryService;

    @Autowired
    private StockService stockService;

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) {

        // ===========================
        // COUNTRY QUERIES
        // ===========================

        System.out.println("\n========== Country Query 1 ==========");
        countryService.searchCountries("ou")
                .forEach(System.out::println);

        System.out.println("\n========== Country Query 2 ==========");
        countryService.searchCountriesSorted("ou")
                .forEach(System.out::println);

        System.out.println("\n========== Country Query 3 ==========");
        countryService.getCountriesStartingWith("Z")
                .forEach(System.out::println);

        // ===========================
        // STOCK QUERIES
        // ===========================

        System.out.println("\n========== Stock Query 1 ==========");
        stockService.getStocks("FB")
                .forEach(System.out::println);

        System.out.println("\n========== Stock Query 2 ==========");
        stockService.getStocksBetweenDates(
                "FB",
                LocalDate.of(2019, 9, 10),
                LocalDate.of(2019, 9, 13))
                .forEach(System.out::println);

        System.out.println("\n========== Stock Query 3 ==========");
        stockService.getStocksGreaterThan("FB", 190)
                .forEach(System.out::println);

        System.out.println("\n========== Stock Query 4 ==========");
        stockService.getStocksOrderByVolume("FB")
                .forEach(System.out::println);

        // ===========================
        // EMPLOYEE DETAILS
        // ===========================

        System.out.println("\n========== Employee Details ==========");

        employeeService.getAllEmployees()
                .forEach(System.out::println);

        // ===========================
        // EMPLOYEE WITH SKILLS
        // ===========================

        System.out.println("\n========== Employee Skills ==========");

        Employee employee = employeeService.getEmployee(1);

        if (employee != null) {
            System.out.println(employee);

            System.out.println("\nSkills:");

            employee.getSkillList().forEach(System.out::println);
        }
        System.out.println("\n========== Permanent Employees (HQL) ==========");

employeeService.getPermanentEmployees()
        .forEach(System.out::println);
        System.out.println("\n========== Permanent Employees (JOIN FETCH) ==========");

employeeService.getPermanentEmployeesJoinFetch()
        .forEach(System.out::println);
        System.out.println("\n========== Permanent Employees (Native Query) ==========");

employeeService.getPermanentEmployeesNative()
        .forEach(System.out::println);
        System.out.println("\n========== Average Salary ==========");

System.out.println("Overall Average Salary : "
        + employeeService.getAverageSalary());

System.out.println("HR Department Average Salary : "
        + employeeService.getAverageSalary(1));

System.out.println("Finance Department Average Salary : "
        + employeeService.getAverageSalary(2));

System.out.println("IT Department Average Salary : "
        + employeeService.getAverageSalary(3));
    }
}