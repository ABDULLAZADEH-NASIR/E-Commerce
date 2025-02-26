package az.texnoera.ecommerce.service.abstracts;


import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.model.request.MailRequest;
import az.texnoera.ecommerce.model.request.UserRequest;
import az.texnoera.ecommerce.model.request.UserRequestForUpdate;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.model.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse addUser( UserRequest userRequest);

    UserResponse getUserById(Long id);

    void deleteUserById(Long id);

    UserResponse updateUserById(Long id, UserRequestForUpdate userRequest);

    Result<UserResponse> getAllUsers(int page, int size);

    String sendMailMessage(MailRequest mailRequest);
}
