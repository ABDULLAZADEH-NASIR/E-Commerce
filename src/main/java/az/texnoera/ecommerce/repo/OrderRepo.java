package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.products LEFT JOIN FETCH o.user")
    Page<Order>findAllOrders(Pageable pageable);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.products" +
            " LEFT JOIN FETCH o.user WHERE o.orderId=:orderId")
    Optional<Order> findOrderByOrderId(Long orderId);
}
