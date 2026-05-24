package finance.management.dto.response;


import finance.management.entity.CategoryType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO for a transaction.
 */
@Data
@Builder
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private String description;
    private CategoryType type;
}
