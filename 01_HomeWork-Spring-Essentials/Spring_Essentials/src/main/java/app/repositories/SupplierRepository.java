package app.repositories;

import app.models.orm.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long>
{
    List<Supplier> getByIsImporter(boolean isImporter);

    List<Supplier> getAllBy();

}
