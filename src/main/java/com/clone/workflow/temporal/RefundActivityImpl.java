package com.clone.workflow.temporal;

import static java.lang.System.*;

public class RefundActivityImpl implements RefundActivity{
    @Override
    public void askForRefund() {
        out.println("Customer asked for a refund");
    }

    @Override
    public void initiateRefund() {
        out.println("Refund inititated");
    }

    @Override
    public void refundProcessed() {
        out.println("Refund processed");
    }

    @Override
    public void refundCompleted() {
        out.println("Refund completed");
    }
}
