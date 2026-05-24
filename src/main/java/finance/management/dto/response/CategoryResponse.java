package finance.management.dto.response;


import finance.management.entity.CategoryType;
import lombok.Builder;
import lombok.Data;

/**
 * Response DTO for a category.
 */
@Data
@Builder
public class CategoryResponse {
    private String name;
    private CategoryType type;
    private boolean isCustom;
}
