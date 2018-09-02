package com.apartdeman.model.service.impl;

import com.apartdeman.model.entity.Complaint;
import com.apartdeman.model.repository.ComplaintRepository;
import com.apartdeman.model.request.ComplaintRequest;
import com.apartdeman.model.service.ComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */
@Service
@AllArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public List<Complaint> getActiveComplaintsByStId(Long stId) {
        return complaintRepository.getActiveComplaintsByStId(stId);
    }

    @Override
    public List<Complaint> getDeletedComplaintsByStId(Long stId) {
        return complaintRepository.getDeletedComplaintsByStId(stId);
    }

    @Override
    public Complaint save(ComplaintRequest complaintRequest, Long userId) {
        Complaint complaint = complaintRequest.toSave(userId);
        return complaintRepository.saveAndFlush(complaint);
    }

    @Override
    public Complaint update(ComplaintRequest complaint, Long userId) {
        return complaintRepository.saveAndFlush(complaint.toUpdate(userId));
    }

    @Override
    public Complaint deleteSoft(Long id, Long userId) {
        Complaint complaint = this.getById(id);
        complaint.setUUser(userId);
        complaint.setIsActv(Boolean.FALSE);
        return complaintRepository.saveAndFlush(complaint);
    }

    @Override
    public Complaint getById(Long id) {
        return complaintRepository.findById(id).get();
    }
}
