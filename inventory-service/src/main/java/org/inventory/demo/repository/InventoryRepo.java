package org.inventory.demo.repository;


import org.inventory.demo.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public interface InventoryRepo extends JpaRepository<Inventory,Long> {

}
