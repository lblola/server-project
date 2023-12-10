package org.example.serverproject.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="client")
@Data
@NoArgsConstructor
public class Client {
    @Id
    @Column(name="client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int client_id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
