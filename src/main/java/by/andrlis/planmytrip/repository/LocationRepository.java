package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
