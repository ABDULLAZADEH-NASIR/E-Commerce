package az.texnoera.ecommerce.maper;

import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.model.request.UserRequest;
import az.texnoera.ecommerce.model.response.*;
import java.util.HashSet;
import java.util.stream.Collectors;


public interface UserMapper {
   static  User userRequestToUser(UserRequest userRequest){
          return User.builder()
                  .userName(userRequest.getUserName())
                  .password(userRequest.getPassword())
                  .userEmails(new HashSet<>())
                  .orders(new HashSet<>())
                  .build();
     }


    static UserResponse userToResponse(User user){
       return UserResponse.builder()
               .userId(user.getUserId())
               .userName(user.getUserName())
               .orders(user.getOrders()
                       .stream()
                       .map(order->OrderResponseForUser.builder()
                               .orderId(order.getOrderId()).build()).collect(Collectors.toSet()))
               .emails(user.getUserEmails()
                       .stream().map(email->UserEmailResponseForUser.builder()
                               .email(email.getEmail()).build()).collect(Collectors.toSet()))
               .build();

    }
}
