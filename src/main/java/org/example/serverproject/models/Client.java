package org.example.serverproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.List;

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
    @NotEmpty
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="password")
    @Size(min = 3, max = 30)
    private String password;

    @ManyToMany
    @JoinTable(
            name="orders",
            joinColumns = @JoinColumn(name="client_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    @JsonBackReference(value = "product-list")
    private List<Product> productList;
    public Client(String name, String address, String password, List<Product> productList) {
        this.name = name;
        this.address = address;
        this.password = password;
        this.productList = productList;
    }
}
