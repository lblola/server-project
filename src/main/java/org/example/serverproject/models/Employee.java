package org.example.serverproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "salary")
    private int salary;

    @JsonBackReference(value="passport")
    @OneToOne(mappedBy = "employee")
    private Passport passport;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

}
