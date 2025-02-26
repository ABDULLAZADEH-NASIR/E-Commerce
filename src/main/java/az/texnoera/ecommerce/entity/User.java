package az.texnoera.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Order>orders=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserEmail> UserEmails=new ArrayList<>();

}
