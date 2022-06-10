package org.demo.consumer.mapper;

import org.demo.consumer.entity.Bill;
import org.demo.consumer.dto.BillDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toEntity(BillDTO billDTO);
    BillDTO toDTO(Bill bill);
}
