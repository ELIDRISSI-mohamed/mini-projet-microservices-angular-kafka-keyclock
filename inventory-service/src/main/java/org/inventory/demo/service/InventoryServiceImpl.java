package org.inventory.demo.service;

import org.inventory.demo.dto.InventoryReqDTO;
import org.inventory.demo.dto.InventoryResDTO;
import org.inventory.demo.entity.Inventory;
import org.inventory.demo.mapper.InventoryMapper;
import org.inventory.demo.repository.InventoryRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepo inventoryRepo;
    private InventoryMapper inventoryMapper;
    public InventoryServiceImpl(InventoryRepo inventoryRepo, InventoryMapper inventoryMapper) {
        this.inventoryRepo = inventoryRepo;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public InventoryResDTO save(InventoryReqDTO inventoryReqDTO) {
        Inventory inventory = inventoryMapper.fromInventoryReqDTO(inventoryReqDTO);
        inventory.setDate(new Date());
        Inventory saveInventory = inventoryRepo.save(inventory);

        return inventoryMapper.fromInventory(inventory);
    }
}
