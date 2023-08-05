package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
