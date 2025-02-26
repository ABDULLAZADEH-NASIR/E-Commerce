package az.texnoera.ecommerce.maper;
import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.model.response.OrderResponse;
import az.texnoera.ecommerce.model.response.ProductResponse;
import lombok.Data;



public interface OrderMaper {

     static   OrderResponse OrderToResponse(Order order) {
            return OrderResponse.builder()
                    .orderId(order.getOrderId())
                    .userId(order.getUser().getUserId())
                    .products(order.getProducts().stream().map(p->
                            ProductResponse.builder()
                                    .productId(p.getProductId())
                                    .productName(p.getProductName())
                                    .imageUrl(p.getImageUrl())
                                    .description(p.getDescription())
                                    .price(p.getPrice())
                                    .build()).toList())
                    .build();
     }


}

