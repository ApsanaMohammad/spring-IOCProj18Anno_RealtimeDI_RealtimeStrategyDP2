// CustomerDAO.java
package com.nt.dao;

import com.nt.bo.CustomerBO;

public interface CustomerDAO {
    int insert(CustomerBO bo) throws Exception;
}
