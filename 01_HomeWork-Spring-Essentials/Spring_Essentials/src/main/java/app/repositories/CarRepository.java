package app.repositories;

import app.models.orm.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long>
{
    @Query("select c from cars as c order by c.model, c.travelledDistance desc ")
    List<Car> getCarsSorted();

    @Query("select c from cars as c where c.make = :make  order by c.model, c.travelledDistance desc ")
    List<Car> getCarsSortedByMake(@Param("make") String make);

}
