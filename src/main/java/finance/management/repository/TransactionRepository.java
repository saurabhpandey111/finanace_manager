package finance.management.repository;

import finance.management.entity.Category;
import finance.management.entity.CategoryType;
import finance.management.entity.Transaction;
import finance.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Transaction entity persistence operations.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds all non-deleted transactions for a user with optional date range, category, and type filters,
     * sorted by date descending.
     */
    @Query("SELECT t FROM Transaction t WHERE t.user = :user " +
            "AND t.isDeleted = false " +
            "AND (:startDate IS NULL OR t.date >= :startDate) " +
            "AND (:endDate IS NULL OR t.date <= :endDate) " +
            "AND (:category IS NULL OR t.category = :category) " +
            "AND (:type IS NULL OR t.category.type = :type) " +
            "ORDER BY t.date DESC, t.id DESC")
    List<Transaction> findByUserWithFilters(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("category") Category category,
            @Param("type") CategoryType type);

    Optional<Transaction> findByIdAndUser(Long id, User user);

    @Query("SELECT COUNT(t) > 0 FROM Transaction t WHERE t.category = :category AND t.isDeleted = false")
    boolean existsByCategory(@Param("category") Category category);

    /** Sum income for a user between two dates, excluding soft-deleted transactions. */
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.user = :user AND t.isDeleted = false " +
            "AND t.category.type = 'INCOME' " +
            "AND t.date >= :startDate AND t.date <= :endDate")
    BigDecimal sumIncomeByUserAndDateRange(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    /** Sum expenses for a user between two dates, excluding soft-deleted transactions. */
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.user = :user AND t.isDeleted = false " +
            "AND t.category.type = 'EXPENSE' " +
            "AND t.date >= :startDate AND t.date <= :endDate")
    BigDecimal sumExpensesByUserAndDateRange(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    /** Get income totals grouped by category name for a month/year, excluding soft-deleted. */
    @Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t " +
            "WHERE t.user = :user AND t.isDeleted = false " +
            "AND t.category.type = 'INCOME' " +
            "AND YEAR(t.date) = :year AND MONTH(t.date) = :month " +
            "GROUP BY t.category.name")
    List<Object[]> sumIncomeGroupedByCategoryForMonth(
            @Param("user") User user,
            @Param("year") int year,
            @Param("month") int month);

    /** Get expense totals grouped by category name for a month/year, excluding soft-deleted. */
    @Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t " +
            "WHERE t.user = :user AND t.isDeleted = false " +
            "AND t.category.type = 'EXPENSE' " +
            "AND YEAR(t.date) = :year AND MONTH(t.date) = :month " +
            "GROUP BY t.category.name")
    List<Object[]> sumExpensesGroupedByCategoryForMonth(
            @Param("user") User user,
            @Param("year") int year,
            @Param("month") int month);

    /** Get income totals grouped by category name for a year, excluding soft-deleted. */
    @Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t " +
            "WHERE t.user = :user AND t.isDeleted = false " +
            "AND t.category.type = 'INCOME' " +
            "AND YEAR(t.date) = :year " +
            "GROUP BY t.category.name")
    List<Object[]> sumIncomeGroupedByCategoryForYear(
            @Param("user") User user,
            @Param("year") int year);

    /** Get expense totals grouped by category name for a year, excluding soft-deleted. */
    @Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t " +
            "WHERE t.user = :user AND t.isDeleted = false " +
            "AND t.category.type = 'EXPENSE' " +
            "AND YEAR(t.date) = :year " +
            "GROUP BY t.category.name")
    List<Object[]> sumExpensesGroupedByCategoryForYear(
            @Param("user") User user,
            @Param("year") int year);
}