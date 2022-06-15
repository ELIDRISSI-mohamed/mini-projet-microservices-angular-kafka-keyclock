package org.demo.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.demo.consumer.entity.Bill;
import org.demo.consumer.service.BillService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@RequestMapping("api/bill")
public class BillController {
    private final BillService billService;


    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/getAll")
    public List<Bill> getAll() {
        log.info("Get all factures");
        return billService.getAll();
    }
}
