package finance.management.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request DTO for updating a transaction (date cannot be changed).
 */
@Data
public class UpdateTransactionRequest {

    @DecimalMin(value = "0.01", message = "Amount must be positive")
    @Digits(integer = 15, fraction = 2, message = "Amount format is invalid")
    private BigDecimal amount;

    private String category;

    private String description;
}