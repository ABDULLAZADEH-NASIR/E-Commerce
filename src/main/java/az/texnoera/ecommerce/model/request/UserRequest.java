package az.texnoera.ecommerce.model.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull
    @Pattern(regexp = "^[A-Za-zƏəĞğİıÖöŞşÜüÇç]{1,11}$",message = "Name is not valid")
    private String userName;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&*!?])[A-Za-z\\d@#$%^&*!?]{4,}$",
            message = "Password is not valid")
    private String password;
    @NotNull
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email is not valid")
    private String email;
}
