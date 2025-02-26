package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    @Query("SELECT u from User  u LEFT JOIN FETCH u.userEmails LEFT JOIN FETCH " +
            " u.orders WHERE u.userId=:id")
    Optional<User> findByUserId(Long id);

    @Query("SELECT DISTINCT u FROM User u  LEFT JOIN FETCH " +
            "u.userEmails LEFT JOIN FETCH u.orders")
    Page<User> findAllUsers(Pageable pageable);

}
