package az.texnoera.ecommerce.maper;
import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.model.response.OrderResponse;
import az.texnoera.ecommerce.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMaper {

       OrderResponse OrderToResponse(Order order) ;


}

