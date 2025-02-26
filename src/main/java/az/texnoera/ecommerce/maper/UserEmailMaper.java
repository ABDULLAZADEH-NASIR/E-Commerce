package az.texnoera.ecommerce.maper;


import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import az.texnoera.ecommerce.model.request.UserEmailRequestForSave;
import az.texnoera.ecommerce.model.response.UserEmailResponse;
import az.texnoera.ecommerce.model.response.UserResponseForUserEmail;


public interface UserEmailMaper {
      static UserEmailResponse UserEmailToUserEmailResponse(UserEmail userEmail) {
          return UserEmailResponse.builder()
                  .userEmailId(userEmail.getUserEmailId())
                  .userResponseForUserEmail(UserResponseForUserEmail.builder()
                          .userId(userEmail.getUserEmailId())
                          .email(userEmail.getEmail())
                          .userName(userEmail.getEmail())
                          .build())
                  .build();
      };

   static UserEmail RequestToUserEmailSave(UserEmailRequestForSave userEmailRequest) {
      return UserEmail.builder()
              .email(userEmailRequest.getEmail())
              .user(User.builder()
                      .userId(userEmailRequest.getUserId())
                      .build())
              .build();

   }
}
