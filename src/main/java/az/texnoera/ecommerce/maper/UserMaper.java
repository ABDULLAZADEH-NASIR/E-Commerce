package az.texnoera.ecommerce.maper;

import az.texnoera.ecommerce.entity.User;
import az.texnoera.ecommerce.model.request.UserRequest;
import az.texnoera.ecommerce.model.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;


@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMaper {
     User UserRequestToUser(UserRequest userRequest);
     UserResponse UserToResponse(User user);

}
