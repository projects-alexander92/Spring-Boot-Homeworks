package app.repositories;

import app.entities.orm.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long>
{
    List<Bike> getAllBy();
    /*
     *I am creating this methods
     *only to test them in the unit tests!!
     */
    @Query("select b from Bike as b where b.gears > 10")
    List<Bike> getAllBikesByGearMoreThen10();
}
