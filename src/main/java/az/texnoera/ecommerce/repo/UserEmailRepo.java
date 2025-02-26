package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.UserEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEmailRepo extends JpaRepository<UserEmail, Long> {

    @Query("SELECT DISTINCT u FROM UserEmail u LEFT JOIN FETCH u.user")
    Page<UserEmail>findAllUserEmail(Pageable pageable);


    @Query("SELECT  u FROM UserEmail u LEFT JOIN FETCH u.user WHERE u.userEmailId=:userEmailId")
    Optional<UserEmail> findUserEmailByUserEmailId(Long userEmailId);
}
