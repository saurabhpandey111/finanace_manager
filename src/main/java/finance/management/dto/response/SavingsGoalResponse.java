package finance.management.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO for a savings goal, including calculated progress fields.
 */
@Data
@Builder
public class SavingsGoalResponse {
    private Long id;
    private String goalName;
    private BigDecimal targetAmount;
    private LocalDate targetDate;
    private LocalDate startDate;
    private BigDecimal currentProgress;
    private Double progressPercentage;
    private BigDecimal remainingAmount;
}
