package finance.management.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO for updating a savings goal.
 */
@Data
public class UpdateSavingsGoalRequest {

    @DecimalMin(value = "0.01", message = "Target amount must be positive")
    @Digits(integer = 15, fraction = 2, message = "Amount format is invalid")
    private BigDecimal targetAmount;

    private LocalDate targetDate;
}