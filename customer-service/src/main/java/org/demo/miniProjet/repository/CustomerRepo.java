package org.demo.miniProjet.repository;

import org.demo.miniProjet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Customer findByName(String name);
    void deleteByName(String name);
   /* @Modifying
    @Query("delete from customer f where f.id=:id")
    void deleteById(@Param("id") String id);
    */

}
