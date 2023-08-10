package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "select p from Location p")
    Page<Location> getLocationsPageable(final Pageable pageable);
}
