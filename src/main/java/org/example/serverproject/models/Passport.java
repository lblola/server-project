package org.example.serverproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="passport")
@Data
@NoArgsConstructor
public class Passport {
    @Id
    @Column(name="passport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passportId;

    @Column(name="passport_num")
    @Range(max = 999999, message = "Passport number < 999999")
    private int passportNum;


    @JsonBackReference(value="employee")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public Passport(int passportNum) {
        this.passportNum = passportNum;
    }
}
