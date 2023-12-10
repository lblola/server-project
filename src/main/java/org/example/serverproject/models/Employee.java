package org.example.serverproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="employee")
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    @Column(name="name")
    private String name;

    @Column(name="salary")
    @Range(min = 1, message = "Min salary > 0")
    private int salary;

    //@JsonManagedReference
    @JsonBackReference
    @OneToOne(optional = false, mappedBy = "employee")
    private Passport passport;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
