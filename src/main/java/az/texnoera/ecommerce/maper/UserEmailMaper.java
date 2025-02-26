package az.texnoera.ecommerce.maper;


import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.entity.UserEmail;
import az.texnoera.ecommerce.model.request.UserEmailRequest;
import az.texnoera.ecommerce.model.request.UserEmailRequestForSave;
import az.texnoera.ecommerce.model.response.UserEmailResponse;
import az.texnoera.ecommerce.model.response.UserResponseForUserEmail;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEmailMaper {
       UserEmailResponse UserEmailToUserEmailResponse(UserEmail userEmail) ;
         UserEmail RequestToUserEmailSave(UserEmailRequestForSave userEmailRequest);


}
