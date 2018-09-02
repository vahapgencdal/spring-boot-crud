package com.apartdeman.model.service;

import com.apartdeman.model.entity.Complaint;
import com.apartdeman.model.request.ComplaintRequest;

import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */
public interface ComplaintService extends BaseService<Complaint,ComplaintRequest>{

    List<Complaint> getActiveComplaintsByStId(Long stId);
    List<Complaint> getDeletedComplaintsByStId(Long stId);

}
