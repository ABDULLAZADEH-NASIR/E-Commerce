package az.texnoera.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_emails")
public class UserEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userEmailId;
    @Column(unique = true, nullable = false)
    private String email;
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;


}
