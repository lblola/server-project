package org.example.serverproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    @Min(value = 1, message = "price > 0")
    private int price;

    /*@JsonBackReference(value = "category")
    @ManyToOne(optional = false)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;*/

    @ManyToMany(mappedBy = "productList")
    @JsonBackReference(value = "clients")
    private List<Client> clients;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
