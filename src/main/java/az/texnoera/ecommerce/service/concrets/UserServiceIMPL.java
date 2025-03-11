package az.texnoera.ecommerce.service.concrets;

import az.texnoera.ecommerce.ExceptionsHandle.BasedExceptionHandle;
import az.texnoera.ecommerce.security.config.MailSend;
import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.entity.Product;
import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import az.texnoera.ecommerce.maper.UserMapper;
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

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    private final UserEmailRepo userEmailRepo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private  final MailSend mailSend;


    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = UserMapper.userRequestToUser(userRequest);
        userRepo.save(user);
        UserEmail userEmail= new UserEmail();
        userEmail.setEmail(userRequest.getEmail());
        userEmail.setUser(user);
        userEmailRepo.save(userEmail);
        user.getUserEmails().add(userEmail);
        userRepo.save(user);
        return UserMapper.userToResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user=userRepo.findByUserId(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));
        return UserMapper.userToResponse(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user=userRepo.findById(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));

        List<Order> orders = orderRepo.findAllById(Collections.singletonList(id));
        userRepo.delete(user);
        for(Order order:orders){
           Set<Product> products= order.getProducts();
           for(Product product:products){
               product.getOrders().remove(order);
               productRepo.save(product);
           }
        }
        orderRepo.deleteAll(orders);
    }

    @Override
    public UserResponse updateUserById(Long id, UserRequestForUpdate userRequest) {
        User user=userRepo.findByUserId(id).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));
        user.setUserName(userRequest.getUserName());
        userRepo.save(user);
        return UserMapper.userToResponse(user);
    }

    @Override
    public Result<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<User> users=userRepo.findAllUsers(pageable);
        List<UserResponse> lists =users.stream().map(UserMapper::userToResponse).toList();
        return new Result<>(lists,page,size,users.getTotalPages());
    }

    @Override
    public String sendMailMessage(MailRequest mailRequest) {
            mailSend.sendMail(mailRequest.
                    getTo(),mailRequest.getSubject(),mailRequest.getBody());
        return "Mail Successfully sent";
    }

}
