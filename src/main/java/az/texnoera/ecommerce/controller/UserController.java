package az.texnoera.ecommerce.controller;

import az.texnoera.ecommerce.model.request.MailRequest;
import az.texnoera.ecommerce.model.request.UserRequest;
import az.texnoera.ecommerce.model.request.UserRequestForUpdate;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.model.response.UserResponse;
import az.texnoera.ecommerce.service.concrets.UserServiceIMPL;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceIMPL userServiceIMPL;

    @PostMapping
    public UserResponse addUser(@RequestBody @Valid UserRequest userRequest) {
        return userServiceIMPL.addUser(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userServiceIMPL.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userServiceIMPL.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,@RequestBody @Valid UserRequestForUpdate userRequest) {
        return userServiceIMPL.updateUserById(id,userRequest);
    }


    @GetMapping
    public Result<UserResponse> getAllUsers(@RequestParam(defaultValue = "0")int page,
                                            @RequestParam(defaultValue = "10")int size) {
        return  userServiceIMPL.getAllUsers(page,size);
    }

    @PostMapping("/send")
    public String sendUser(@RequestBody @Valid MailRequest mailRequest) {
        return userServiceIMPL.sendMailMessage(mailRequest);
    }
}
