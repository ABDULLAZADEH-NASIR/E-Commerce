package az.texnoera.ecommerce.maper;
import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.model.response.OrderResponse;
import az.texnoera.ecommerce.model.response.ProductResponse;
import lombok.Data;

import java.util.stream.Collectors;


public interface OrderMaper {

     static   OrderResponse OrderToResponse(Order order) {
            return OrderResponse.builder()
                    .orderId(order.getOrderId())
                    .userId(order.getUser().getUserId())
                    .userName(order.getUser().getUserName())
                    .products(order.getProducts().stream().map(p->
                            ProductResponse.builder()
                                    .productId(p.getProductId())
                                    .productName(p.getProductName())
                                    .imageUrl(p.getImageUrl())
                                    .description(p.getDescription())
                                    .price(p.getPrice())
                                    .build()).collect(Collectors.toSet()))
                    .build();
     }
}

