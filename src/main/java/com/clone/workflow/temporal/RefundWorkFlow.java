package com.clone.workflow.temporal;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface RefundWorkFlow {
    public static final String QUEUE_NAME = "Customer_Refund";

    @WorkflowMethod
    void startApprovalWorkflow();

    @SignalMethod
    void signalRefundInitiated();

    @SignalMethod
    void signalRefundProcessed();

    @SignalMethod
    void signalRefundCompleted();
}
