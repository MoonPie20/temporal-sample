package com.clone.workflow.controller;

import com.clone.workflow.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefundController {

    @Autowired
    RefundService refundService;

    @PostMapping("/startRefund")
    public String createOrder(@RequestParam("id") String id) {
        refundService.askForRefund(id);
        return "Refund Started";
    }

    @PostMapping("/refundInitiated")
    public String orderAccepted(@RequestParam("id") String id) {
        refundService.makeRefundInitited(id);
        return "Refund Initiated";
    }

    @PostMapping("/refundProcessed")
    public String orderPickedUp(@RequestParam("id") String id) {
        refundService.makeRefundProcessed(id);
        return "Refund Processed";
    }

    @PostMapping("/refundCompleted")
    public String orderDelivered(@RequestParam("id") String id) {
        refundService.makeRefundCompleted(id);
        return "Refund Completed";
    }
}
