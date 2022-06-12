package org.demo.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.demo.consumer.dto.BillDTO;
import org.demo.consumer.entity.Bill;
import org.demo.consumer.mapper.BillMapper;
import org.demo.consumer.repository.BillRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
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

        System.out.println("Received ==> "+billDTO.toString());
        Bill bill = billMapper.billDTOToBill(billDTO);
        billRepository.save(bill);
        writeBill(billDTO);
    }

    private void writeBill(BillDTO bill) throws FileNotFoundException {
        log.info("Write in file");
        File file;
        FileWriter fr;
        try {
            file = new File("bill-facturation.txt");
            fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(bill.getId() + "," + bill.getCustomer() + "," + bill.getPrice()+"\r\n");

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
