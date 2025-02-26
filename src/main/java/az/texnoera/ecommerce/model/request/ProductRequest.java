package az.texnoera.ecommerce.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class ProductRequest {
    @NotNull
    @Pattern(regexp = "^[A-Za-zƏəĞğİıÖöŞşÜüÇç]{1,11}$",message = "FirstName is not valid")
    private String name;
    @NotNull
    private String description;
    @NotNull
    @Positive
    private int price;
    @NotNull
    private String imageUrl;
}
