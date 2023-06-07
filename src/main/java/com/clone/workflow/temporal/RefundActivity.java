package com.clone.workflow.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface RefundActivity {
    @ActivityMethod
    void askForRefund();

    @ActivityMethod
    void initiateRefund();

    @ActivityMethod
    void refundProcessed();

    @ActivityMethod
    void refundCompleted();

}
