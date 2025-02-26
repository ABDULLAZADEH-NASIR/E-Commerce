package az.texnoera.ecommerce.service.abstracts;

import az.texnoera.ecommerce.model.request.UserEmailRequest;
import az.texnoera.ecommerce.model.request.UserEmailRequestForSave;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.model.response.UserEmailResponse;

public interface UserEmailService {
    UserEmailResponse saveMail(UserEmailRequestForSave userEmailRequest);
    void deleteMailById(Long id);
    UserEmailResponse updateUserEmailById(Long id, UserEmailRequestForSave userEmailRequest);
    Result<UserEmailResponse> getAllEmails(int page, int pageSize);
    UserEmailResponse getUserEmailById(Long id);
}
