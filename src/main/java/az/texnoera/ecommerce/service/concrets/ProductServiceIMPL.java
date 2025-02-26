package az.texnoera.ecommerce.service.concrets;


import az.texnoera.ecommerce.ExceptionsHandle.BasedExceptionHandle;
import az.texnoera.ecommerce.entity.Product;
import az.texnoera.ecommerce.maper.ProductMaper;
import az.texnoera.ecommerce.model.enums.ExceptionStatusCode;
import az.texnoera.ecommerce.model.request.ProductRequest;
import az.texnoera.ecommerce.model.response.ProductResponse;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.repo.ProductRepo;
import az.texnoera.ecommerce.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceIMPL implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public void deleteProductById(Long id) {
         Product product=productRepo.findById(id).
                 orElseThrow(() -> new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                         ExceptionStatusCode.PRODUCT_NOT_FOUND));
         productRepo.delete(product);
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {
        Product product=productRepo.findById(id)
                .orElseThrow(() -> new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.PRODUCT_NOT_FOUND));
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        productRepo.save(product);
        return ProductMaper.ProductToProductResponse(product);
    }

    @Override
    public Result<ProductResponse> getAllProducts( int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> products;


        products=productRepo.findAll(pageable);

        List<ProductResponse>productResponses=products.stream().
                map(ProductMaper::ProductToProductResponse).toList();

        return new Result<>(productResponses,page,pageSize,products.getTotalPages());
    }


    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
       Product product=ProductMaper.ProductRequestToProduct(productRequest);
       productRepo.save(product);
        return ProductMaper.ProductToProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product=productRepo.findById(id).
                orElseThrow(() -> new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.PRODUCT_NOT_FOUND));
        return ProductMaper.ProductToProductResponse(product);
    }
}
