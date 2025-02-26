package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.UserEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepo extends JpaRepository<UserEmail, Long> {
}
