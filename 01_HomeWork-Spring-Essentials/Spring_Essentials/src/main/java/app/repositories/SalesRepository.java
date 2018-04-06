package app.repositories;

import app.models.orm.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository<Sale, Long>
{
    List<Sale> getAllBy();

    @Query("select s from sales s where s.discount >= 0.1")
    List<Sale> getAllWithDiscount();

    @Query("select s from sales s where s.discount = :discount")
    List<Sale> getAllWithFixedDiscount(@Param("discount") double discount);
}
