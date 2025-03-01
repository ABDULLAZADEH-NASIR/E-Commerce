package az.texnoera.ecommerce.service.concrets;
import az.texnoera.ecommerce.ExceptionsHandle.BasedExceptionHandle;
import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.entity.Product;
import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.maper.OrderMaper;
import az.texnoera.ecommerce.model.enums.ExceptionStatusCode;
import az.texnoera.ecommerce.model.request.OrderRequestForAddProduct;
import az.texnoera.ecommerce.model.request.OrderRequestForSaveOrder;
import az.texnoera.ecommerce.model.request.OrderRequestForUpdate;
import az.texnoera.ecommerce.model.response.OrderResponse;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.repo.OrderRepo;
import az.texnoera.ecommerce.repo.ProductRepo;
import az.texnoera.ecommerce.repo.UserRepo;
import az.texnoera.ecommerce.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;


    @Override
    public OrderResponse saveOrder(OrderRequestForSaveOrder order) {
       User user = userRepo.findById(order.getUserId())
               .orElseThrow(() -> new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                       ExceptionStatusCode.USER_NOT_FOUND));
       Product product=productRepo.findById(order.getProductId())
               .orElseThrow(() -> new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                       ExceptionStatusCode.PRODUCT_NOT_FOUND));
       Order newOrder=new Order();
       newOrder.setUser(user);
       newOrder.setProducts(new HashSet<>());
       newOrder.getProducts().add(product);
       orderRepo.save(newOrder);
       user.getOrders().add(newOrder);
       userRepo.save(user);
       product.getOrders().add(newOrder);
       productRepo.save(product);
       return OrderMaper.OrderToResponse(newOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepo.findAllOrders(pageable);
        List<OrderResponse>orderResponses=orders.stream().map(OrderMaper::OrderToResponse).toList();
        return new Result<>(orderResponses,page,size,orders.getTotalPages()).getData();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order=orderRepo.findOrderByOrderId(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.ORDER_NOT_FOUND));
        return OrderMaper.OrderToResponse(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order=orderRepo.findById(id)
                .orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.ORDER_NOT_FOUND));
        User user=userRepo.findById(order.getUser().getUserId())
                .orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));

        order.getProducts().forEach(product -> product.getOrders().remove(order));
        orderRepo.delete(order);
        user.getOrders().remove(order);
        userRepo.save(user);
    }

    @Override
    public OrderResponse updateOrderById(Long id, OrderRequestForUpdate order) {
        Order order1=orderRepo.findById(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.ORDER_NOT_FOUND));

        Product product=productRepo.findById(order.getExistingProductId())
                .orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.PRODUCT_NOT_FOUND));

        Product product1=productRepo.findById(order.getNewProductId()).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.PRODUCT_NOT_FOUND));

        Iterator<Product> iterator = order1.getProducts().iterator();
        boolean exist=false;

        while (iterator.hasNext()){
            Product p=iterator.next();
            if (p.getProductId().equals(product.getProductId())){
                order1.getProducts().remove(p);
                orderRepo.save(order1);
                p.getOrders().remove(order1);
                productRepo.save(product);
                exist=true;
                break;
            }
        }
           if (!exist){
            throw new BasedExceptionHandle(HttpStatus.NOT_FOUND, ExceptionStatusCode.PRODUCT_NOT_FOUND);
        }
        order1.getProducts().add(product1);
        orderRepo.save(order1);
        product1.getOrders().add(order1);
        productRepo.save(product);
        return OrderMaper.OrderToResponse(order1);
    }

    @Override
    public OrderResponse updateOrderAddProduct(Long id,
                                               OrderRequestForAddProduct orderRequestForAddProduct) {
        Order order=orderRepo.findById(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.ORDER_NOT_FOUND));
        Product product=productRepo.findById(orderRequestForAddProduct.getAddNewProductId()).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.PRODUCT_NOT_FOUND));

          order.getProducts().add(product);
          orderRepo.save(order);
          product.getOrders().add(order);
          productRepo.save(product);
        return OrderMaper.OrderToResponse(order);
    }
}

