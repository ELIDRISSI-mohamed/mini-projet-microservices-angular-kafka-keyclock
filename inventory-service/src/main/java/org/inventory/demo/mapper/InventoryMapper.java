package org.inventory.demo.mapper;

import org.inventory.demo.dto.InventoryReqDTO;
import org.inventory.demo.dto.InventoryResDTO;
import org.inventory.demo.entity.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryResDTO fromInventory(Inventory invoice);
    Inventory fromInventoryReqDTO(InventoryReqDTO invoiceReqDTO);
}
