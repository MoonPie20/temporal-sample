package com.clone.workflow;

import com.clone.workflow.temporal.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

@SpringBootApplication
public class SwiggyCloneApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(SwiggyCloneApplication.class, args);
		WorkerFactory factory = appContext.getBean(WorkerFactory.class);
		Activity signUpActivity = appContext.getBean(Activity.class);
		RefundActivity refundActivity = appContext.getBean(RefundActivity.class);
		Worker worker = factory.newWorker(WorkFlow.QUEUE_NAME);
		worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
		worker.registerActivitiesImplementations(signUpActivity);
		Worker refundworker = factory.newWorker(RefundWorkFlow.QUEUE_NAME);
		refundworker.registerWorkflowImplementationTypes(RefundWorkFlowImpl.class);
		refundworker.registerActivitiesImplementations(refundActivity);
		factory.start();
	}

}
