package az.texnoera.ecommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEmailResponse {
    private Long id;
    private UserResponseForUserEmail userEmailResponse;
}
