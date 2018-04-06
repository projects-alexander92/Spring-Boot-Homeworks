package app.repositories;

import app.models.orm.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    @Query("select c from customers  as c")
    List<Customer> findAllCustomers();

    @Query(value = "select c from customers as c order by c.birthDate asc, c.isYoungDriver desc")
    List<Customer> findAllByBirthDateAscending();

    @Query(value = "select c from customers as c order by c.birthDate desc, c.isYoungDriver desc")
    List<Customer> findAllByBirthDateDescending();

    @Modifying
    @Query("update customers as c set c.name= :name, c.isYoungDriver= :isYoungDriver, c.birthDate = :birthDate where c.id = :id")
    void updateById(@Param("id") long id, @Param("name") String name, @Param("isYoungDriver") boolean isYoungDriver, @Param("birthDate") Date date);

}
