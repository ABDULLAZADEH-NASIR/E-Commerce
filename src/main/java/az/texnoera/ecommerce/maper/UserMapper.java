package az.texnoera.ecommerce.maper;

import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import az.texnoera.ecommerce.model.request.UserRequest;
import az.texnoera.ecommerce.model.response.*;

import java.util.ArrayList;
import java.util.List;


public interface UserMapper {
   static  User userRequestToUser(UserRequest userRequest){
          return User.builder()
                  .userName(userRequest.getUserName())
                  .password(userRequest.getPassword())
                  .UserEmails(new ArrayList<>())
                  .orders(new ArrayList<>())
                  .build();
     }



    static UserResponse userToResponse(User user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .orders(user.getOrders().stream().map(o->
                        OrderResponseForUser.builder()
                                .orderId(o.getOrderId())
                                .build()).toList())
                .emails(user.getUserEmails().stream().
                        map(e->UserEmailResponseForUser.builder()
                                .email(e.getEmail())
                        .build()).toList())
                .build();
     }

}
