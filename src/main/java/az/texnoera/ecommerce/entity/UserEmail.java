package az.texnoera.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEmail userEmail = (UserEmail) o;
        return Objects.equals(userEmailId, userEmail.userEmailId) &&
                Objects.equals(email, userEmail.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmailId, email);
    }
}
