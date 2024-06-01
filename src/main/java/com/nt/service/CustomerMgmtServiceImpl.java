// CustomerMgmtServiceImpl.java
package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nt.bo.CustomerBO;
import com.nt.dao.CustomerDAO;
import com.nt.dto.CustomerDTO;

@Service("customerService")
public final class CustomerMgmtServiceImpl implements CustomerMgmtService {
	@Autowired
	@Qualifier("customerDAO")
    private CustomerDAO dao;

    public CustomerMgmtServiceImpl(CustomerDAO dao) {
        this.dao = dao;
    }

    @Override
    public String calculateSimpleInterest(CustomerDTO dto) throws Exception {
        // Calculate simple interest
        float principleAmount = dto.getPamt();
        float time = dto.getTime();
        float rate = dto.getRate();

        float simpleInterest = (principleAmount * rate * time) / 100;

        // Insert customer data into the database
        try {
            int count = insertCustomer(dto);
            // Return the result as a formatted string
            return count == 1 ? "Customer registered successfully. Simple interest amount: " + simpleInterest
                    : "Customer registration failed";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private int insertCustomer(CustomerDTO dto) throws Exception {
        CustomerBO bo = new CustomerBO();
        bo.setCustName(dto.getCustName());
        bo.setCustAddrs(dto.getCustAddrs());
        bo.setPamt(dto.getPamt());
        bo.setTime(dto.getTime());
        bo.setRate(dto.getRate());
        return dao.insert(bo);
    }
}
