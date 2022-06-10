package org.demo.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.demo.consumer.dto.BillDTO;
import org.demo.consumer.entity.Bill;
import org.demo.consumer.mapper.BillMapper;
import org.demo.consumer.repository.BillRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillServiceImpl(BillRepository billRepository, BillMapper billMapper) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }


    @KafkaListener(topics = "FACTURATION", containerFactory = "kafkaListenerContainerFactory")
    public void consumeImportOffreTopic(BillDTO billDTO) throws Exception {
        //TODO SAVE BILL IN DATABASE
        //Bill bill = billMapper.toEntity(billDTO);
        //bill = billRepository.save(bill);
        //TODO SAVE BILL IN FILE
        writeBill(billDTO);
    }

    private void writeBill(BillDTO bill) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(bill.getId() + "," + bill.getCustomer() + "," + bill.getPrice());
        }
    }
}
