package tom.study.domain.intersection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tom.study.domain.intersection.model.entity.Intersection;

import java.util.List;

public interface IntersectionRepository extends JpaRepository<Intersection, Long> {
    @Query("SELECT l FROM Intersection l WHERE FUNCTION('ST_Distance_Sphere', FUNCTION('POINT', :longitude, :latitude), FUNCTION('POINT', l.longitude, l.latitude)) < :distance")
    List<Intersection> findNearbyLocations(@Param("longitude") Double longitude, @Param("latitude") Double latitude, @Param("distance") int distance);
}
