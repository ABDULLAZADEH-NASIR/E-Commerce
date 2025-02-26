package az.texnoera.ecommerce.service.concrets;

import az.texnoera.ecommerce.ExceptionsHandle.BasedExceptionHandle;
import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import az.texnoera.ecommerce.maper.UserEmailMaper;
import az.texnoera.ecommerce.model.enums.ExceptionStatusCode;
import az.texnoera.ecommerce.model.request.UserEmailRequestForSave;
import az.texnoera.ecommerce.model.response.Result;
import az.texnoera.ecommerce.model.response.UserEmailResponse;
import az.texnoera.ecommerce.repo.UserEmailRepo;
import az.texnoera.ecommerce.repo.UserRepo;
import az.texnoera.ecommerce.service.abstracts.UserEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEmailServiceIMPL implements UserEmailService {
    private final UserEmailRepo userEmailRepo;
    private final UserRepo userRepo;
    private final UserEmailMaper userEmailMaper;

    @Override
    public UserEmailResponse saveMail(UserEmailRequestForSave userEmailRequest) {
       User user=userRepo.findById(userEmailRequest.getUserId()).
               orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                       ExceptionStatusCode.USER_NOT_FOUND));

        UserEmail userEmail = userEmailMaper.RequestToUserEmailSave(userEmailRequest);
        userEmail.setUser(user);
        user.getUserEmails().add(userEmail);
        userRepo.save(user);
        userEmailRepo.save(userEmail);
        return userEmailMaper.UserEmailToUserEmailResponse(userEmail);
    }

    @Override
    public void deleteMailById(Long id) {
        UserEmail userEmail=userEmailRepo.findById(id)
                .orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_EMAIL_NOT_FOUND));
        User user=userRepo.findById(userEmail.getUser().getId()).
                orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_NOT_FOUND));
        user.getUserEmails().remove(userEmail);
        userRepo.save(user);
        userEmailRepo.delete(userEmail);
    }

    @Override
    public UserEmailResponse updateUserEmailById(Long id, UserEmailRequestForSave userEmailRequest) {
        UserEmail userEmail=userEmailRepo.findById(id)
                .orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_EMAIL_NOT_FOUND));
        userEmail.setEmail(userEmailRequest.getEmail());
        userEmailRepo.save(userEmail);
        return userEmailMaper.UserEmailToUserEmailResponse(userEmail);
    }

    @Override
    public Result<UserEmailResponse> getAllEmails(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserEmail> userEmails=userEmailRepo.findAll(pageable);
        List<UserEmailResponse>userEmailResponses=userEmails.stream()
                .map(userEmailMaper::UserEmailToUserEmailResponse).toList();
        return new Result<>(userEmailResponses,page,pageSize,userEmails.getTotalPages());
    }

    @Override
    public UserEmailResponse getUserEmailById(Long id) {
        UserEmail userEmail=userEmailRepo.findById(id)
                .orElseThrow(()->new BasedExceptionHandle(HttpStatus.NOT_FOUND,
                        ExceptionStatusCode.USER_EMAIL_NOT_FOUND));
        return userEmailMaper.UserEmailToUserEmailResponse(userEmail);

    }
}
