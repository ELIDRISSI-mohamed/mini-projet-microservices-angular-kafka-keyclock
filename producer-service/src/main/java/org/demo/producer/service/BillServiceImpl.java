package org.demo.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.demo.producer.dto.BillDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
@EnableScheduling
public class BillServiceImpl implements BillService {


    private final KafkaTemplate<String, BillDTO> template;

    List<String> customers = Arrays.asList("customer-1", "customer-2", "customer-3", "customer-4",
        "customer-5", "customer-6", "customer-7", "customer-8", "customer-9", "customer-10");

    public BillServiceImpl(KafkaTemplate<String, BillDTO> template) {
        this.template = template;
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void sendBillsToKafka () {
        BillDTO bill = new BillDTO();
        bill.setId((long) (Math.random() * 999999999));
        bill.setCustomer(customers.get((int) (Math.random() * 10)));
        bill.setPrice(Math.random() * 9999);
        System.out.println(bill);
        template.send("FACTURATION", bill);
    }
}
