// CustomerMgmtService.java
package com.nt.service;

import com.nt.dto.CustomerDTO;

public interface CustomerMgmtService {
    String calculateSimpleInterest(CustomerDTO dto) throws Exception;
}
