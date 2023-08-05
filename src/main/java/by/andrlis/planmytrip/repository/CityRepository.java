package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
