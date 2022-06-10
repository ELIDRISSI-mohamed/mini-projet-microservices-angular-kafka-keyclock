 package org.inventory.demo.service;

 import org.inventory.demo.dto.InventoryReqDTO;
 import org.inventory.demo.dto.InventoryResDTO;


public interface InventoryService {
    InventoryResDTO save(InventoryReqDTO invoiceReqDTO);
}
