package az.texnoera.ecommerce.controller;

import az.texnoera.ecommerce.model.request.UserEmailRequestForSave;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.model.response.UserEmailResponse;
import az.texnoera.ecommerce.service.concrets.UserEmailServiceIMPL;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emails")
public class EmailController {
    private final UserEmailServiceIMPL userEmailService;

    @PostMapping
    public UserEmailResponse saveEmail(@RequestBody @Valid UserEmailRequestForSave userEmailRequest) {
        return userEmailService.saveMail(userEmailRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMailById(@PathVariable Long id) {
        userEmailService.deleteMailById(id);
    }

    @PutMapping("/{id}")
    public UserEmailResponse updateEmailById(@PathVariable Long id,
                                             @RequestBody @Valid UserEmailRequestForSave userEmailRequest) {

      return  userEmailService.updateUserEmailById(id,userEmailRequest);
    }

    @GetMapping
    public Result<UserEmailResponse> getAllEmails(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        return userEmailService.getAllEmails(page,pageSize);
    }

    @GetMapping("/{id}")
    public UserEmailResponse getEmailById(@PathVariable Long id) {
        return userEmailService.getUserEmailById(id);
    }

}
