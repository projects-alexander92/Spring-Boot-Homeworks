package app.repositories;

import app.entities.orm.Capital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CapitalRepository extends CrudRepository<Capital, Long>
{
    @Query(value = "select c.name from capitals as c")
    List<String> getCapitalNames();

    List<Capital> findByNameIn(List<String> capitalNames);
}
