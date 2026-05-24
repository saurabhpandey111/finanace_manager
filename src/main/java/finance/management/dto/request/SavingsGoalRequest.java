package finance.management.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO for creating a savings goal.
 */
@Data
public class SavingsGoalRequest {

    @NotBlank(message = "Goal name is required")
    private String goalName;

    @NotNull(message = "Target amount is required")
    @DecimalMin(value = "0.01", message = "Target amount must be positive")
    @Digits(integer = 15, fraction = 2, message = "Amount format is invalid")
    private BigDecimal targetAmount;

    @NotNull(message = "Target date is required")
    private LocalDate targetDate;

    private LocalDate startDate;
}