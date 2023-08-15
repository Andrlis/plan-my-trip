package by.andrlis.planmytrip.repository;

import by.andrlis.planmytrip.entity.ContentSourceType;
import by.andrlis.planmytrip.entity.Location;
import by.andrlis.planmytrip.entity.LocationContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationContentRepository extends JpaRepository<LocationContent, Long> {
    @Query("SELECT count(1) FROM LocationContent lc " +
            "JOIN lc.location l " +
            "JOIN lc.contentSource cs " +
            "WHERE l = :location " +
            "AND cs.type = :contentSourceType")
    Integer countOfLocationContentByLocationAndType(@Param("location") Location location,
                                                    @Param("contentSourceType") ContentSourceType type);
}
