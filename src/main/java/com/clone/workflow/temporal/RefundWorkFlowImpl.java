package com.clone.workflow.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class RefundWorkFlowImpl implements RefundWorkFlow{
    private final RetryOptions retryoptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(50000).build();
    private final ActivityOptions options = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(30))
            .setRetryOptions(retryoptions).build();

    private final RefundActivity activity = Workflow.newActivityStub(RefundActivity.class, options);
    boolean isRefundInitiated = false;
    boolean isRefundProcessed = false;
    boolean isRefundCompleted = false;
    @Override
    public void startApprovalWorkflow() {

        activity.askForRefund();
        Workflow.await(() -> isRefundInitiated);

        Workflow.await(() -> isRefundProcessed);

        Workflow.await(() -> isRefundCompleted);


    }

    @Override
    public void signalRefundInitiated() {
        activity.initiateRefund();
        isRefundInitiated=true;

    }

    @Override
    public void signalRefundProcessed() {
        activity.refundProcessed();
        isRefundProcessed=true;
    }

    @Override
    public void signalRefundCompleted() {
        activity.refundCompleted();
        isRefundCompleted=true;
    }
}
