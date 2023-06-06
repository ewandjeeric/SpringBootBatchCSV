package com.batchii.processor;

import com.batchii.dao.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;



public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    // Creates a logger
    private static final Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);

    @Override
    public Customer process(Customer customer) throws Exception {
        final String firstName = customer.getFirstName().toUpperCase();
        final String lastName = customer.getLastName().toUpperCase();
        // Creates a new instance of Person
        final Customer transformedCustomer = new Customer(firstName, lastName);
        // logs the person entity to the application logs
        logger.info("Converting (" + customer + ") into (" + transformedCustomer + ")");
        return transformedCustomer;
    }
}
