package az.texnoera.ecommerce.repo;

import az.texnoera.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.orders")
    Page<Product>findAllProducts(Pageable pageable);

    @Query("SELECT  p FROM Product p LEFT JOIN FETCH p.orders WHERE p.productId=:productId")
   Optional <Product> findByProductId(Long productId);
}
