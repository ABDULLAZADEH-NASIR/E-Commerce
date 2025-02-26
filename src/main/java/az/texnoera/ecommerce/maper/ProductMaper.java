package az.texnoera.ecommerce.maper;


import az.texnoera.ecommerce.entity.Product;
import az.texnoera.ecommerce.model.request.ProductRequest;
import az.texnoera.ecommerce.model.response.ProductResponse;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.PageRequest;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMaper {
     Product ProductRequestToProduct(ProductRequest product);
     ProductResponse ProductToProductResponse(Product product);
}
