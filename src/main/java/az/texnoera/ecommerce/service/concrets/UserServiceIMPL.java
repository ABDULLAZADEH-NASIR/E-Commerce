package az.texnoera.ecommerce.service.concrets;

import az.texnoera.ecommerce.ExceptionsHandle.BasedExceptionHandle;
import az.texnoera.ecommerce.config.MailSend;
import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.entity.Product;
import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import az.texnoera.ecommerce.maper.UserMaper;
import az.texnoera.ecommerce.model.enums.ExceptionStatusCode;
import az.texnoera.ecommerce.model.request.MailRequest;
import az.texnoera.ecommerce.model.request.UserRequest;
import az.texnoera.ecommerce.model.request.UserRequestForUpdate;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.model.response.UserResponse;
import az.texnoera.ecommerce.repo.OrderRepo;
import az.texnoera.ecommerce.repo.ProductRepo;
import az.texnoera.ecommerce.repo.UserEmailRepo;
import az.texnoera.ecommerce.repo.UserRepo;
import az.texnoera.ecommerce.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    private final UserEmailRepo userEmailRepo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private  final MailSend mailSend;
    private final UserMaper userMaper;


    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = userMaper.UserRequestToUser(userRequest);
        userRepo.save(user);
        UserEmail userEmail=new UserEmail();
        userEmail.setEmail(userRequest.getEmail());
        userEmail.setUser(user);
        userEmailRepo.save(userEmail);
        user.getUserEmails().add(userEmail);
        userRepo.save(user);
        return userMaper.UserToResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user=userRepo.findById(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));
        return userMaper.UserToResponse(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user=userRepo.findById(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));

        List<Order> orders = orderRepo.findAllById(Collections.singletonList(id));
        userRepo.delete(user);
        for(Order order:orders){
           List<Product>products= order.getProducts();
           for(Product product:products){
               product.getOrders().remove(order);
               productRepo.save(product);
           }
        }
        orderRepo.deleteAll(orders);
    }

    @Override
    public UserResponse updateUserById(Long id, UserRequestForUpdate userRequest) {
        User user=userRepo.findById(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));
        user.setUsername(userRequest.getUsername());
        userRepo.save(user);
        return userMaper.UserToResponse(user);
    }

    @Override
    public Result<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<User> users=userRepo.findAll(pageable);
        List<UserResponse>list=users.stream().map(userMaper::UserToResponse).toList();
        return new Result<>(list,page,size,users.getTotalPages());
    }

    @Override
    public String sendMailMessage(MailRequest mailRequest) {
        Thread thread=new Thread(()->{
            mailSend.sendMail(mailRequest.
                    getTo(),mailRequest.getSubject(),mailRequest.getBody());
        });
        thread.start();
        return "Mail Successfully sent";
    }

}
