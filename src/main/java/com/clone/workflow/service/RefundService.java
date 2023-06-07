package com.clone.workflow.service;

import com.clone.workflow.temporal.RefundWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    @Autowired
    WorkflowServiceStubs workflowServiceStubs;

    @Autowired
    WorkflowClient workflowClient;

    public  void  askForRefund(String workflowId){
        RefundWorkFlow workflow = createWorkFlowConnection(workflowId);
        WorkflowClient.start(workflow::startApprovalWorkflow);
    }
    public void makeRefundInitited(String workflowId) {
        RefundWorkFlow workflow = workflowClient.newWorkflowStub(RefundWorkFlow.class, "Order_" + workflowId);
        workflow.signalRefundInitiated();
    }
    public void makeRefundProcessed(String workflowId) {
        RefundWorkFlow workflow = workflowClient.newWorkflowStub(RefundWorkFlow.class, "Order_" + workflowId);
        workflow.signalRefundProcessed();
    }
    public void makeRefundCompleted(String workflowId) {
        RefundWorkFlow workflow = workflowClient.newWorkflowStub(RefundWorkFlow.class, "Order_" + workflowId);
        workflow.signalRefundCompleted();
    }
    public RefundWorkFlow createWorkFlowConnection(String id) {
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(RefundWorkFlow.QUEUE_NAME)
                .setWorkflowId("Order_" + id).build();
        return workflowClient.newWorkflowStub(RefundWorkFlow.class, options);
    }
}
