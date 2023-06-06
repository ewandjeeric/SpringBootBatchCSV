package com.batchii.batchconfig;

import com.batchii.dao.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

    private static final Logger log =  LoggerFactory.getLogger(JobCompletionListener.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public JobCompletionListener(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // When the batch process is completed the the users in the database are retrieved and logged on the application logs
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB COMPLETED! verify the results");
            customerRepository.findAll()
                    .forEach(person -> log.info("Found (" + person + ">) in the database.") );
        }
    }
}
