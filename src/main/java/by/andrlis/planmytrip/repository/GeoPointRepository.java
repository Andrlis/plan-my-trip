package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.GeoPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeoPointRepository extends JpaRepository<GeoPoint, Long> {

    Optional<GeoPoint> findByLatitudeAndLongitude(String latitude, String longitude);
}
