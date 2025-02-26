package az.texnoera.ecommerce.maper;


import az.texnoera.ecommerce.entity.Product;
import az.texnoera.ecommerce.model.request.ProductRequest;
import az.texnoera.ecommerce.model.response.ProductResponse;


public interface ProductMaper {

  static Product ProductRequestToProduct(ProductRequest product){
       return Product.builder()
               .productName(product.getProductName())
               .description(product.getDescription())
               .price(product.getPrice())
               .imageUrl(product.getImageUrl())
               .build();
  }



    static ProductResponse ProductToProductResponse(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
