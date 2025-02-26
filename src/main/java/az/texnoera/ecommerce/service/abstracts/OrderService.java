package az.texnoera.ecommerce.service.abstracts;

import az.texnoera.ecommerce.model.request.OrderRequestForAddProduct;
import az.texnoera.ecommerce.model.request.OrderRequestForSaveOrder;
import az.texnoera.ecommerce.model.request.OrderRequestForUpdate;
import az.texnoera.ecommerce.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saveOrder(OrderRequestForSaveOrder order);
    List<OrderResponse> getAllOrders(int pageNo, int pageSize);
    OrderResponse getOrderById(Long id);
    void deleteOrderById(Long id);
    OrderResponse updateOrderById(Long id , OrderRequestForUpdate order);
    OrderResponse updateOrderAddProduct(Long id, OrderRequestForAddProduct order);
}
