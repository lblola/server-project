package org.example.serverproject.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientAuthDTO {
    @Column(name="name")
    private String name;
    @Size(max = 30)
    private String password;
}
