package az.texnoera.ecommerce.model.request;

import az.texnoera.ecommerce.model.response.UserResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEmailRequest {
    @NotNull
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email is not valid")
    private String email;
    @NotNull
    @Positive
    private Long userId;
    @NotNull
    @Pattern(regexp = "^[A-Za-zƏÖĞÜÇŞIəöğüçşı]{2,11}$",message = "FirstName is not valid")
    private String userName;
}
