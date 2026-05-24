package finance.management.repository;

import finance.management.entity.Category;
import finance.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Category entity persistence operations.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /** Returns all default (system) categories. */
    List<Category> findByCustomFalse();

    /** Returns all custom categories for a given user. */
    List<Category> findByUserAndCustomTrue(User user);

    /** Finds a default category by name (case-insensitive). */
    Optional<Category> findByNameIgnoreCaseAndCustomFalse(String name);

    /** Finds a custom category by name for a specific user (case-insensitive). */
    Optional<Category> findByNameIgnoreCaseAndUser(String name, User user);

    /** Checks whether a custom category name already exists for a user. */
    boolean existsByNameIgnoreCaseAndUser(String name, User user);

    /**
     * Finds a category by name that is accessible to the user:
     * either a default category or one of the user's custom categories.
     */
    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name) " +
            "AND (c.custom = false OR c.user = :user)")
    Optional<Category> findAccessibleByName(@Param("name") String name, @Param("user") User user);

    /**
     * Finds a category by id that is accessible to the user:
     * either a default category or one of the user's custom categories.
     */
    @Query("SELECT c FROM Category c WHERE c.id = :id " +
            "AND (c.custom = false OR c.user = :user)")
    Optional<Category> findAccessibleById(@Param("id") Long id, @Param("user") User user);
}
