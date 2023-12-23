package org.example.serverproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="admin")
@NoArgsConstructor
@Data
public class Admin {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name="password")
    @Size(min = 3, max = 30)
    private String password;

    public Admin(String name, String password){
        this.name = name;
        this.password = password;
    }
}
