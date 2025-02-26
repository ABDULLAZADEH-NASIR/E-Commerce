package az.texnoera.ecommerce.service.abstracts;

import az.texnoera.ecommerce.model.request.ProductRequest;
import az.texnoera.ecommerce.model.response.ProductResponse;
import az.texnoera.ecommerce.model.response.Result;

import java.util.List;

public interface ProductService {
    void deleteProductById(Long id);
    ProductResponse updateProductById(Long id, ProductRequest productRequest);
    Result<ProductResponse> getAllProducts( int page, int pageSize);
    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long id);
}
