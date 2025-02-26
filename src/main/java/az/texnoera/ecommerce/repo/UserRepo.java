package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


//    @EntityGraph(attributePaths = {"orders","UserEmails"})
//    Page<User> findAll(Pageable pageable);
}
