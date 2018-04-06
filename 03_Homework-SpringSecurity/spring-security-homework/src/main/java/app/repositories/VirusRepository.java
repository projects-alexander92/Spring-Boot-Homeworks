package app.repositories;

import app.entities.orm.Virus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirusRepository extends CrudRepository<Virus, Long>
{
    List<Virus> getAllBy();
}
