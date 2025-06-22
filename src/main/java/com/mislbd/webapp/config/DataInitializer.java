package com.mislbd.webapp.config;

import com.mislbd.webapp.entity.Employee;
import com.mislbd.webapp.repsitory.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
        return args -> {
            // Initialize the database with some sample data
            /*employeeRepository.saveAll(
                    List.of(
                    new Employee("Meftaul", "Haque", "meftaulHaque@gmail.com",
                                 "01712345678", "Dhaka, Bangladesh", new Date()),
                    new Employee("John", "Doe", "test@gmail.com",
                                 "01812345678", "New York, USA", new Date())
                    )
            );*/
            System.out.println("Database initialized with sample data.");
        };
    }

}
