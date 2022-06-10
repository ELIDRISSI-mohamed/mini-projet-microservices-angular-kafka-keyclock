package org.demo.consumer.controller;

import org.demo.consumer.entity.Bill;
import org.demo.consumer.service.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/bill")
public class BillController {
    private final BillService billService;


    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getAll() {
        return billService.getAll();
    }
}
