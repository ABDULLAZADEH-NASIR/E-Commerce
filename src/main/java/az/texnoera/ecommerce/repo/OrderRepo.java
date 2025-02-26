package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderRepo extends JpaRepository<Order, Long> {
}
