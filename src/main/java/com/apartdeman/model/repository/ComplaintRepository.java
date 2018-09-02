package com.apartdeman.model.repository;

import com.apartdeman.model.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    /**
     * To fetch Active Complaints by stId
     * @param stId
     * @return
     */
    @Query("SELECT complaint FROM Complaint complaint WHERE complaint.stId=:theStId and complaint.isActv=1")
    List<Complaint> getActiveComplaintsByStId(@Param("theStId") Long stId);

    /**
     * To fetch Deleted Complaints by stId
     * @param stId
     * @return
     */
    @Query("SELECT complaint FROM Complaint complaint WHERE complaint.stId=:theStId and complaint.isActv=0")
    List<Complaint> getDeletedComplaintsByStId(@Param("theStId") Long stId);

}
