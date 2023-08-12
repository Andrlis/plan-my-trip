package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.City;
import by.andrlis.planmytrip.entity.Country;
import by.andrlis.planmytrip.entity.Location;
import by.andrlis.planmytrip.entity.LocationCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "select p from Location p")
    Page<Location> findPageableLocations(Pageable pageable);

    @Query("SELECT l FROM Location l WHERE (:category is null or l.category = :category) and (:country is null"
            + " or l.country = :country) and (:city is null or l.city = :city)")
    Page<Location> findPageableLocationsByCategoryAndCountryAndCity(@Param("category") LocationCategory category,
                                                                    @Param("country") Country country,
                                                                    @Param("city") City city, Pageable pageable);
}
