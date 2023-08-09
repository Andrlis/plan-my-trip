package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.LocationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationCategoryRepository extends JpaRepository<LocationCategory, Long> {

    Optional<LocationCategory> findByTitle(String title);
}
