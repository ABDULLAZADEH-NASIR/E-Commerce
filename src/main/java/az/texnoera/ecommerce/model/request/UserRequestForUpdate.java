package az.texnoera.ecommerce.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestForUpdate {
    @NotNull
    @Pattern(regexp = "^[A-Za-zƏÖĞÜÇŞIəöğüçşı]{2,11}$",message = "Name is not valid")
    private String userName;
}
