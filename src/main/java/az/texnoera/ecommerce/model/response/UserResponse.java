package az.texnoera.ecommerce.model.response;

import az.texnoera.ecommerce.entity.Order;
import az.texnoera.ecommerce.entity.UserEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long userId;
    private String userName;
    private List<OrderResponseForUser> orders=new ArrayList<>();
    private List<UserEmailResponseForUser> emails=new ArrayList<>();
}
