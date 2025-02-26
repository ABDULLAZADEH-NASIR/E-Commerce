package az.texnoera.ecommerce.controller;

import az.texnoera.ecommerce.model.request.ProductRequest;
import az.texnoera.ecommerce.model.response.ProductResponse;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.service.concrets.ProductServiceIMPL;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceIMPL productService;

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProductById(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        return productService.updateProductById(id, productRequest);
    }


    @GetMapping
    public Result<ProductResponse> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getAllProducts(page, pageSize);
    }

    @PostMapping
    public ProductResponse addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

   @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
   }
}
