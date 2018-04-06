package app.repositories;

import app.models.orm.Part;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Long>
{
    @Query("select p from parts as p")
    List<Part> getAll();

    @Modifying
    @Query("update parts as p set p.price = :price, p.quantity = :quantity where p.id = :id")
    void editById(@Param("price") double price, @Param("quantity") int quantity, @Param("id") long id);
}
