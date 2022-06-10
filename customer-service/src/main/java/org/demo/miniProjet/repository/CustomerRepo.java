package org.demo.miniProjet.repository;

import org.demo.miniProjet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Customer findByName(@PathVariable String name);
    void deleteByName(@PathVariable String name);
   /* @Modifying
    @Query("delete from customer f where f.id=:id")
    void deleteById(@Param("id") String id);
    */

}
