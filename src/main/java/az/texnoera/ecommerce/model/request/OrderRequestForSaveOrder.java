package az.texnoera.ecommerce.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class OrderRequestForSaveOrder {
    @NotNull
    @Positive
    private Long userId;
    @NotNull
    @Positive
    private Long productId;
}
