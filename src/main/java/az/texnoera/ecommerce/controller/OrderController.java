package az.texnoera.ecommerce.controller;

import az.texnoera.ecommerce.model.request.OrderRequestForAddProduct;
import az.texnoera.ecommerce.model.request.OrderRequestForSaveOrder;
import az.texnoera.ecommerce.model.request.OrderRequestForUpdate;
import az.texnoera.ecommerce.model.response.OrderResponse;
import az.texnoera.ecommerce.service.concrets.OrderServiceIMPL;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderServiceIMPL orderServiceIMPL;

   @PostMapping
    public OrderResponse saveOrder(@RequestBody @Valid OrderRequestForSaveOrder order) {
       return orderServiceIMPL.saveOrder(order);

 }

   @GetMapping
    public List<OrderResponse> getAllOrders(@RequestParam(defaultValue = "0")int page,
                                            @RequestParam(defaultValue = "10")int pageSize) {
       return orderServiceIMPL.getAllOrders(page,pageSize);
  }

   @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
       return orderServiceIMPL.getOrderById(id);
   }

   @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
       orderServiceIMPL.deleteOrderById(id);
   }


    @PatchMapping("/{id}")
    public OrderResponse updateOrderProduct(@PathVariable Long id,
                                           @RequestBody @Valid OrderRequestForUpdate order) {
       return orderServiceIMPL.updateOrderById(id,order);
   }

    @PutMapping("/{id}")
    public OrderResponse updateOrderAddProduct(@PathVariable Long id,
                                            @RequestBody @Valid OrderRequestForAddProduct order) {
        return orderServiceIMPL.updateOrderAddProduct(id,order);
    }

}
